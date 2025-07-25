package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import com.example.demo.repository.StudentRepository;
import com.example.demo.model.Student;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.example.demo.ElasticSearch.StudentSearchRepository;


@SpringBootTest 
@TestPropertySource(properties = {
    "spring.profiles.active=test",
    "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration"
})
@Transactional
public class StudentServiceIntegrationTest
{
    @Autowired
    private StudentServiceInterface studentService;

    @Autowired
    private StudentRepository studentRepository;

    @MockitoBean 
    private StudentSearchRepository studentSearchRepository;


    @Test
    public void testClearAllData_WithTestProfile() {
        // Arrange
        Student student1 = new Student();
        student1.setName("John Doe");
        student1.setEmail("john.doe@example.com");
        student1.setState("CA");
        student1.setPhone("1234567890");
        student1.setAge(20);
        studentRepository.save(student1);

        // Act
        studentService.clearAllData();

        // Assert
        List<Student> students = studentRepository.findAll();
        assertEquals(0, students.size());
        
    }

    @Test
    public void testServiceInjection() {
        assertTrue(studentService instanceof TestStudentService);
        assertNotNull(studentService);
        assertNotNull(studentRepository);
    }

    @Test
    public void testServiceTransactionalBehavior() {
        // Arrange
        Student student1 = new Student();
        student1.setName("John Doe");
        student1.setEmail("john.doe@example.com");
        student1.setState("CA");
        student1.setPhone("1234567890");
        student1.setAge(20);
        
        // Act & Assert - Test that each test runs in its own transaction
        studentRepository.save(student1);
        List<Student> students = studentRepository.findAll();
        assertEquals(1, students.size());
        
        // This data will be rolled back automatically after test due to @Transactional
    }
}
