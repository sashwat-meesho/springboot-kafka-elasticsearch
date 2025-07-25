package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import java.util.List;

@Component
@Profile("!test")  // Don't run during tests
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        Student student1 = new Student();
        student1.setName("John Doe");
        student1.setAge(20);
        student1.setEmail("john.doe@example.com");
        student1.setPhone("1234567890");
        student1.setState("CA");
        
        Student student2 = new Student();
        student2.setName("Jane Smith");
        student2.setAge(22);
        student2.setEmail("jane.smith@example.com");
        student2.setPhone("0987654321");
        student2.setState("NY");

        Student student3 = new Student();
        student3.setName("Jim Beam");
        student3.setAge(24);
        student3.setEmail("jim.beam@example.com");
        student3.setPhone("1111111111");
        student3.setState("TX");

        Student student4 = new Student();
        student4.setName("John Doe");
        student4.setAge(20);
        student4.setEmail("john.doe@example.com");
        student4.setPhone("1234567890");
        student4.setState("CA");
        
        studentRepository.saveAll(List.of(student1, student2, student3, student4));
    }
    
}