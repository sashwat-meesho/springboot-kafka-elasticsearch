package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.ElasticSearch.StudentSearchRepository;
import com.example.demo.ElasticSearch.StudentDocument;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.util.List;
@Service
public class StartupSyncService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentSearchRepository studentSearchRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void syncH2ToElasticsearch() {
        System.out.println("🔄 Syncing H2 DB to Elasticsearch...");

        studentSearchRepository.deleteAll();

        List<Student> students = studentRepository.findAll();
        System.out.println("📦 Found " + students.size() + " students in H2:");

        List<StudentDocument> documents = students.stream().map(s ->
            new StudentDocument(s.getId(), s.getName(), s.getEmail(), s.getState(), Integer.parseInt(s.getPhone()), s.getAge())
        ).toList();

        studentSearchRepository.saveAll(documents);

        System.out.println("✅ ES Sync complete.");
    }
}
