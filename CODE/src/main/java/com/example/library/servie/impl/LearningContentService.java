package com.example.library.servie.impl;

import com.example.library.model.LearningContent;
import com.example.library.repository.ILearningRepository;
import com.example.library.servie.ILearningContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningContentService  implements ILearningContentService {
    @Autowired
    private ILearningRepository learningRepository;
    @Override
    public LearningContent addLearningContent(LearningContent content) {
        return learningRepository.save(content);
    }

    @Override
    public LearningContent update(LearningContent content) {
        return learningRepository.save(content);
    }

    @Override
    public List<LearningContent> getAll() {
        return learningRepository.findAll();
    }

    @Override
    public void delete(String id) {
        learningRepository.deleteById(id);

    }

    @Override
    public LearningContent getById(String id) {
        return learningRepository.findById(id).get();
    }
}
