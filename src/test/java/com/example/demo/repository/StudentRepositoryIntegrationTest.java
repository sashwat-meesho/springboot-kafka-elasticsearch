package com.example.demo.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import java.util.List;
import com.example.demo.model.Student;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(properties = {"spring.profiles.active=test"})
public class StudentRepositoryIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindAllStudents() {
        List<Student> students = studentRepository.findAll();
        assertEquals(0, students.size());
    }

    @Test
    public void testDeleteAllStudents() {
        studentRepository.deleteAll();
        List<Student> students = studentRepository.findAll();
        assertEquals(0, students.size());
    }

    @Test
    public void testSaveAndFindStudent() 
    {
        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setState("CA");
        student.setPhone("1234567890");
        student.setAge(20);
        studentRepository.save(student);
        List<Student> students = studentRepository.findAll();
        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
        assertEquals("john.doe@example.com", students.get(0).getEmail());
        assertEquals("CA", students.get(0).getState());
        assertEquals("1234567890", students.get(0).getPhone());
        assertEquals(20, students.get(0).getAge());
    }
 
}
