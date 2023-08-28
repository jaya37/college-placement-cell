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
@Builder
@AllArgsConstructor
public class Company {
    @Id
    private String id;
    private String  name;
    private String  email;
    private String password;
    private String address;


}
