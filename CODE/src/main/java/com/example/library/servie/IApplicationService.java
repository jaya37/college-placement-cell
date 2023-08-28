package com.example.library.servie;

import com.example.library.model.Application;

import java.util.List;

public interface IApplicationService {
    Application getById(String id);
    Application  update(Application application);
    List<Application> getAll();
    Application addApplication(Application application);
    void deleteById(String  id);
}
