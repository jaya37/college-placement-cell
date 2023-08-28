package com.example.library.servie;

import com.example.library.model.LearningContent;

import java.util.List;

public interface ILearningContentService {
    LearningContent addLearningContent(LearningContent content);
    LearningContent update(LearningContent content);
    List<LearningContent> getAll();
    void delete(String  id);
    LearningContent getById(String id);
}
