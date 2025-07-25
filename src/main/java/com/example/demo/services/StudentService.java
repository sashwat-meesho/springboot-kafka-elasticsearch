package com.example.demo.services;

import com.example.demo.repository.StudentRepository;
import com.example.demo.ElasticSearch.StudentSearchRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentSearchRepository studentSearchRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void clearAllData() {
        System.out.println("🧹 Clearing H2 and Elasticsearch data...");

        // 1. Clear DB
        studentRepository.deleteAll();

        // 2. Clear Elasticsearch
        studentSearchRepository.deleteAll();

        // 3. Reset H2 auto-increment ID
        entityManager
            .createNativeQuery("ALTER TABLE student ALTER COLUMN id RESTART WITH 1")
            .executeUpdate();

        System.out.println("✅ Data cleared and ID reset.");
    }
}
