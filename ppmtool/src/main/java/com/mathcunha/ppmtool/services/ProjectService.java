package com.mathcunha.ppmtool.services;

import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project){
        //logic
        return projectRepository.save(project);
    }
}
