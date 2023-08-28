package com.example.library.servie.impl;

import com.example.library.model.Job;
import com.example.library.repository.IJobRepository;
import com.example.library.servie.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements IJobService {
    @Autowired
    private IJobRepository jobRepository;
    @Override
    public Job getById(String id) {
        return jobRepository.findById(id).get();
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(String id) {
         jobRepository.deleteById(id);
    }
}
