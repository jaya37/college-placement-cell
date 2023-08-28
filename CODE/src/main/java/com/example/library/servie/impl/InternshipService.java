package com.example.library.servie.impl;

import com.example.library.model.Internship;
import com.example.library.repository.IInternshipRepository;
import com.example.library.servie.IInternshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipService implements IInternshipService {

    @Autowired
    private IInternshipRepository internshipRepository;
    @Override
    public Internship getById(String id) {
        return internshipRepository.findById(id).get();
    }

    @Override
    public List<Internship> getAll() {
        return internshipRepository.findAll();
    }

    @Override
    public Internship addJob(Internship job) {
        return internshipRepository.save(job);
    }

    @Override
    public Internship updateJob(Internship job) {
        return internshipRepository.save(job);
    }

    @Override
    public void deleteJob(String id) {
            internshipRepository.deleteById(id);
    }
}
