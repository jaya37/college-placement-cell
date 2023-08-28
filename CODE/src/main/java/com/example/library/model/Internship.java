package com.example.library.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;

@Document
@AllArgsConstructor @NoArgsConstructor
@ToString @Data
@Builder
public class Internship {
    @Id
    private String id;
    private String duration;
    private Integer fees;
    private String subject;
    private String status= "added";
}
