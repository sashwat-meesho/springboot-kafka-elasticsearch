package com.example.demo.ElasticSearch;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Repository
@Profile("test")  // Only active during tests
@Primary  // Use this as the primary bean when available
public class TestStudentSearchRepository {

    private List<StudentDocument> mockStorage = new ArrayList<>();

    public StudentDocument save(StudentDocument student) {
        System.out.println("📄 [TEST MODE] Saving to mock Elasticsearch: " + student.getName());
        mockStorage.add(student);
        return student;
    }

    public void deleteAll() {
        System.out.println("🧹 [TEST MODE] Clearing mock Elasticsearch");
        mockStorage.clear();
    }

    public List<StudentDocument> findByName(String name) {
        System.out.println("🔍 [TEST MODE] Searching by name: " + name);
        return mockStorage.stream().filter(s -> s.getName().contains(name)).toList();
    }

    public Optional<StudentDocument> findById(Long id) {
        System.out.println("🔍 [TEST MODE] Searching by ID: " + id);
        return mockStorage.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public List<StudentDocument> findByAge(int age) {
        System.out.println("🔍 [TEST MODE] Searching by age: " + age);
        return mockStorage.stream().filter(s -> s.getAge() == age).toList();
    }

    public List<StudentDocument> findByAgeBetween(int minAge, int maxAge) {
        System.out.println("🔍 [TEST MODE] Searching by age between: " + minAge + "-" + maxAge);
        return mockStorage.stream().filter(s -> s.getAge() >= minAge && s.getAge() <= maxAge).toList();
    }

    public List<StudentDocument> findByEmail(String email) {
        System.out.println("🔍 [TEST MODE] Searching by email: " + email);
        return mockStorage.stream().filter(s -> s.getEmail().contains(email)).toList();
    }

    public List<StudentDocument> findByState(String state) {
        System.out.println("🔍 [TEST MODE] Searching by state: " + state);
        return mockStorage.stream().filter(s -> s.getState().contains(state)).toList();
    }

    public List<StudentDocument> findByPhone(int phone) {
        System.out.println("🔍 [TEST MODE] Searching by phone: " + phone);
        return mockStorage.stream().filter(s -> s.getPhone() == phone).toList();
    }
} 