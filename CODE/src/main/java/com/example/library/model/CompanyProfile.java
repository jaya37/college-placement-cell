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
public class CompanyProfile {
    @Id
    private String  id;
    private String companyName;
    private  String companyId;
    private String product;
    private Number strength;
    private String awards;
    private Number annualIncome;
    private String  history;
    private String  basicSalary;

}
