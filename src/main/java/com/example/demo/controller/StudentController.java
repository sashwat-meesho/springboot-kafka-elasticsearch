package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.kafka.KafkaProducerInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.ElasticSearch.StudentSearchRepository;
import com.example.demo.ElasticSearch.StudentDocument;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.example.demo.services.StudentServiceInterface;

//import org.springframework.cache.annotation.Cacheable;

@RestController
@Profile("!test")  // Don't load during tests since it depends on Elasticsearch
public class StudentController 
{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private KafkaProducerInterface kafkaProducer;

    @Autowired
    private StudentSearchRepository studentSearchRepository;

    @Autowired
    private StudentServiceInterface studentService;

    //@Cacheable("students")
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        System.out.println("👉 Fetching from database...");
        return studentRepository.findAll();
    }
    
    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        System.out.println("👉 Creating student...");
        Student savedStudent = studentRepository.save(student);
        kafkaProducer.sendMessage("Student created: " + savedStudent.getName());

        StudentDocument studentDocument = new StudentDocument(savedStudent.getId(), savedStudent.getName(), savedStudent.getEmail(), savedStudent.getState(), Integer.parseInt(savedStudent.getPhone()), savedStudent.getAge());  
        studentSearchRepository.save(studentDocument);
        System.out.println("👉 Student created and saved to Elasticsearch");
        return savedStudent;
    }
    
    
    @GetMapping("/students/email")
    public List<String> getAllStudentEmails() {
        List<Student> students = studentRepository.findAll();
        List<String> emails = new ArrayList<>();
        for (Student student : students) {
            emails.add(student.getEmail());
        }
        return emails;
    }

    @GetMapping("/students/search")
    public Iterable<StudentDocument> searchStudentsByName(@RequestParam("name") String name) {
        return studentSearchRepository.findByName(name);
    }

    @GetMapping("/students/search/id")
    public StudentDocument searchStudentById(@RequestParam("id") Long id) {
        return studentSearchRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @GetMapping("/students/search/age")
    public List<StudentDocument> searchStudentsByAge(@RequestParam("age") int age) {
        return studentSearchRepository.findByAge(age);
    }

    @GetMapping("/students/search/age/between")
    public List<StudentDocument> searchStudentsByAgeBetween(@RequestParam("minAge") int minAge, @RequestParam("maxAge") int maxAge) {
        return studentSearchRepository.findByAgeBetween(minAge, maxAge);
    }
    @DeleteMapping("/students/clear")
    public String clearAllData() {
        studentService.clearAllData();
        return "🧹 H2 and Elasticsearch cleared. ID reset.";
    }   
}
