package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.example.demo.repository.StudentRepository;
import com.example.demo.ElasticSearch.StudentSearchRepository;
import com.example.demo.ElasticSearch.StudentDocument;
import com.example.demo.kafka.KafkaProducerInterface;
import com.example.demo.services.StudentServiceInterface;
import com.example.demo.model.Student;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(StudentController.class) 
public class StudentControllerTest 
{

    @Autowired
    private MockMvc mockMvc;
    
    @MockitoBean private StudentRepository studentRepository;
    @MockitoBean private KafkaProducerInterface kafkaProducer;  
    @MockitoBean private StudentSearchRepository studentSearchRepository;
    @MockitoBean private StudentServiceInterface studentService;

    @Test
    public void testGetAllStudents() throws Exception {
        // ARRANGE
        Student student1 = new Student();
        student1.setId(1L);
        student1.setName("John Doe");
        student1.setEmail("john.doe@example.com");
        
        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        student2.setEmail("jane.smith@example.com");
        
        List<Student> students = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(students);

        // ACT & ASSERT
        mockMvc.perform(get("/students"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("John Doe"))
            .andExpect(jsonPath("$[1].name").value("Jane Smith"));
    }

    @Test
    public void testCreateStudent() throws Exception {
        // ARRANGE
        Student savedStudent = new Student();
        savedStudent.setId(1L);
        savedStudent.setName("John Doe");
        savedStudent.setEmail("john.doe@example.com");
        savedStudent.setState("CA");
        savedStudent.setPhone("1234567890");
        savedStudent.setAge(20);
        
        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        // ACT & ASSERT
        mockMvc.perform(post("/students")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"state\":\"CA\",\"phone\":\"1234567890\",\"age\":20}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Doe"))
            .andExpect(jsonPath("$.email").value("john.doe@example.com"));
            
        verify(kafkaProducer, times(1)).sendMessage(contains("Student created: John Doe"));
        verify(studentSearchRepository, times(1)).save(any(StudentDocument.class));
    }

    @Test
    public void testGetAllStudentEmail() throws Exception {
        // ARRANGE
        Student student1 = new Student();
        student1.setEmail("john.doe@example.com");
        
        Student student2 = new Student();
        student2.setEmail("jane.smith@example.com");
        
        List<Student> students = Arrays.asList(student1, student2);
        when(studentRepository.findAll()).thenReturn(students);

        // ACT & ASSERT
        mockMvc.perform(get("/students/email"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").value("john.doe@example.com"))
            .andExpect(jsonPath("$[1]").value("jane.smith@example.com"));
    }

    @Test
    public void testSearchStudentsByName() throws Exception {
        // ARRANGE
        StudentDocument doc1 = new StudentDocument(1L, "John Doe", "john.doe@example.com", "CA", 1234567890, 20);
        List<StudentDocument> documents = Arrays.asList(doc1);
        when(studentSearchRepository.findByName("John Doe")).thenReturn(documents);

        // ACT & ASSERT
        mockMvc.perform(get("/students/search").param("name", "John Doe"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].name").value("John Doe"));
    }
    
    
}
