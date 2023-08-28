package com.example.library.servie;

import com.example.library.model.Job;

import java.util.List;

public interface IJobService {
    Job getById(String id);
    List<Job> getAll();
    Job addJob(Job job);
    Job updateJob(Job job);
    void deleteJob(String id);
}
