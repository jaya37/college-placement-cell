package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {
    @Id
    private String  id;
    private String studentId;
    private String studentName;
    private String email;
    private Number phoneNumber;
    private String qualification;
    private String resume;
    private  Integer marks;
    private String doB;
    private String branch;
    private String status="created";
    @DocumentReference
    List<Job> jobList;
}
