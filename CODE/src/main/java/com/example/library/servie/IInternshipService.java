package com.example.library.servie;

import com.example.library.model.Internship;

import java.util.List;

public interface IInternshipService {

    Internship getById(String id);
    List<Internship> getAll();
    Internship addJob(Internship job);
    Internship updateJob(Internship job);
    void deleteJob(String id);
}
