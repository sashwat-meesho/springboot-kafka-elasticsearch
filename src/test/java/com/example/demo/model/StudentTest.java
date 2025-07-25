package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
//import com.example.demo.model.Student;

public class StudentTest 
{
    @Test
    public void testStudent() 
    {
        // ARRANGE - Create a student object
        Student student = new Student();

        //test the name
        String expectedName = "John Doe";
        
        // ACT - Call the method we're testing
        student.setName(expectedName);
        String actualName = student.getName();
        
        // ASSERT - Check if it worked
        assertEquals(expectedName, actualName);

        //test the age
        int expectedAge = 20;
        student.setAge(expectedAge);
        int actualAge = student.getAge();
        assertEquals(expectedAge, actualAge);

        //test the email
        String expectedEmail = "john.doe@example.com";
        student.setEmail(expectedEmail);
        String actualEmail = student.getEmail();
        assertEquals(expectedEmail, actualEmail);

        //test the phone            
        String expectedPhone = "1234567890";
        student.setPhone(expectedPhone);
        String actualPhone = student.getPhone();
        assertEquals(expectedPhone, actualPhone);

        //test the state                
        String expectedState = "CA";
        student.setState(expectedState);
        String actualState = student.getState();
        assertEquals(expectedState, actualState);

        //test the id   
        Long expectedId = 1L;
        student.setId(expectedId);
        Long actualId = student.getId();
        assertEquals(expectedId, actualId);
  
    }
    
    @Test
    public void testStudentWithNullName() {
        Student student = new Student();
        student.setName(null);
        assertNull(student.getName());
    }   
}
