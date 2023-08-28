package com.example.library.servie.impl;

import com.example.library.model.Application;
import com.example.library.repository.IApplicationRepository;
import com.example.library.servie.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private IApplicationRepository applicationRepository;

    @Override
    public Application getById(String id) {
        return applicationRepository.findById(id).get();
    }

    @Override
    public Application update(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    @Override
    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public void deleteById(String id) {
        applicationRepository.deleteById(id);
    }
}
