package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    private String id;
    private String jobTitle;
    private String description;
    private String  domain;
    private String salary;
    private String companyName;
    private Number  eligibleMarks;
    private String  department;
    private String  location;
    private String  status="posted";
}
