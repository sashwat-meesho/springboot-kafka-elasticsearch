package com.example.demo.ElasticSearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface StudentSearchRepository extends ElasticsearchRepository<StudentDocument, Long> {
    List<StudentDocument> findByName(String name);
    List<StudentDocument> findByEmail(String email);
    List<StudentDocument> findByState(String state);
    List<StudentDocument> findByPhone(int phone);
    List<StudentDocument> findByAge(int age);
    List<StudentDocument> findByAgeBetween(int minAge, int maxAge);
}
