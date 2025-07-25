package com.example.demo.ElasticSearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentDocumentTest 
{
    @Test
    public void testDefaultConstructor() {
        StudentDocument studentDocument = new StudentDocument();
        studentDocument.setId(1L);
        studentDocument.setName("John Doe");
        studentDocument.setEmail("john.doe@example.com");
        studentDocument.setState("CA");
        studentDocument.setPhone(1234567890);
        studentDocument.setAge(20);

        assertEquals(1L, studentDocument.getId());
        assertEquals("John Doe", studentDocument.getName());
        assertEquals("john.doe@example.com", studentDocument.getEmail());
        assertEquals("CA", studentDocument.getState());
        assertEquals(1234567890, studentDocument.getPhone());
        assertEquals(20, studentDocument.getAge());
    }

    @Test
    public void testAllArgsConstructor() {
        StudentDocument studentDocument = new StudentDocument(1L, "John Doe", "john.doe@example.com", "CA", 1234567890, 20);
        assertEquals(1L, studentDocument.getId());
        assertEquals("John Doe", studentDocument.getName());
        assertEquals("john.doe@example.com", studentDocument.getEmail());
        assertEquals("CA", studentDocument.getState());
        assertEquals(1234567890, studentDocument.getPhone());
        assertEquals(20, studentDocument.getAge());
    }

    @Test
    public void testSetters() {
        StudentDocument studentDocument = new StudentDocument();
        studentDocument.setId(1L);
        studentDocument.setName("John Doe");
        studentDocument.setEmail("john.doe@example.com");
        studentDocument.setState("CA");
    }

    @Test
    public void testGetters() {
        StudentDocument studentDocument = new StudentDocument(1L, "John Doe", "john.doe@example.com", "CA", 1234567890, 20);
        assertEquals(1L, studentDocument.getId());
        assertEquals("John Doe", studentDocument.getName());
        assertEquals("john.doe@example.com", studentDocument.getEmail());
        assertEquals("CA", studentDocument.getState());
        assertEquals(1234567890, studentDocument.getPhone());
        assertEquals(20, studentDocument.getAge());
    }

    @Test
    public void testWithNullValues() {
        StudentDocument studentDocument = new StudentDocument();
        studentDocument.setId(null);
        studentDocument.setName(null);
        studentDocument.setEmail(null);
        studentDocument.setState(null);
        studentDocument.setPhone(0);
        studentDocument.setAge(0);

        assertEquals(null, studentDocument.getId());
        assertEquals(null, studentDocument.getName());
        assertEquals(null, studentDocument.getEmail());
        assertEquals(null, studentDocument.getState());
    }

    @Test
    public void testWithInvalidValues() {
        StudentDocument studentDocument = new StudentDocument();
        studentDocument.setId(-1L);
        studentDocument.setName("");
        studentDocument.setEmail("invalid-email");
        studentDocument.setState("Invalid");
        studentDocument.setPhone(0);
        studentDocument.setAge(0);

        assertEquals(-1L, studentDocument.getId());
        assertEquals("", studentDocument.getName());
        assertEquals("invalid-email", studentDocument.getEmail());
        assertEquals("Invalid", studentDocument.getState());
        assertEquals(0, studentDocument.getPhone());
        assertEquals(0, studentDocument.getAge());
    }
}
