package com.example.demo.ElasticSearch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mockito.Mock;

@ExtendWith(SpringExtension.class)  
public class StudentSearchRepositoryTest 
{
    @Mock
    private StudentSearchRepository studentSearchRepository;

    @Test
    public void testFindByName() {
        List<StudentDocument> students = studentSearchRepository.findByName("John Doe");
        assertEquals(0, students.size());
    }

    @Test
    public void testFindByEmail() {
        List<StudentDocument> students = studentSearchRepository.findByEmail("john.doe@example.com");
        assertEquals(0, students.size());
    }

    @Test
    public void testFindByState() {
        List<StudentDocument> students = studentSearchRepository.findByState("CA");
        assertEquals(0, students.size());
    }

    @Test
    public void testFindByPhone() {
        List<StudentDocument> students = studentSearchRepository.findByPhone(1234567890);
        assertEquals(0, students.size());
    }

    @Test
    public void testFindByAge() {
        List<StudentDocument> students = studentSearchRepository.findByAge(20);
        assertEquals(0, students.size());
    }

    @Test
    public void testFindByAgeBetween() {
        List<StudentDocument> students = studentSearchRepository.findByAgeBetween(18, 22);
        assertEquals(0, students.size());
    }
}
