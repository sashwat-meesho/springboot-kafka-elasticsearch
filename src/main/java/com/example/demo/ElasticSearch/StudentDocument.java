package com.example.demo.ElasticSearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "students")
public class StudentDocument {
    @Id
    private Long id;

    
    private String name;
    private String email;
    private String state;
    private int phone;
    private int age;

    public StudentDocument() {
    }

    public StudentDocument(Long id, String name, String email, String state, int phone, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.state = state;
        this.phone = phone;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
