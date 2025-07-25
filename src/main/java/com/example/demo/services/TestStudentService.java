package com.example.demo.services;

import com.example.demo.repository.StudentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")  // Test version - database only
public class TestStudentService implements StudentServiceInterface {

    @Autowired
    private StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void clearAllData() {
        System.out.println("🧹 Clearing H2 data (test mode)...");

        // 1. Clear DB only
        studentRepository.deleteAll();

        // 2. Reset H2 auto-increment ID
        entityManager
            .createNativeQuery("ALTER TABLE student ALTER COLUMN id RESTART WITH 1")
            .executeUpdate();

        System.out.println("✅ Test data cleared and ID reset.");
    }
} 