package com.mathcunha.ppmtool.services;

import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdate(Project project){
        project.setIdentifier(project.getIdentifier().toUpperCase());
        return projectRepository.save(project);
    }

    public Project findByIdentifier(String id){
        return projectRepository.findByIdentifier(id);
    }

    public Iterable<Project> findAll(){
        return projectRepository.findAll();
    }

    public Boolean deleteByIdentifier(String id){
        Project project = findByIdentifier(id);
        if (project != null) {
            projectRepository.delete(project);
            return true;
        }
        return false;
    }
}
