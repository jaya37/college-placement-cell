package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application {
    @Id
    private String  id;
    private String studentId;
    private String  studentName;
    private String  email;
    private String studentDepartment;
    private String jobId;
    private String jobTitle;
    private String description;
    private String  domain;
    private String salary;
    private String companyName;
    private Number  eligibleMarks;
    private String  department;
    private String  location;
    private String  status="applied";
}
