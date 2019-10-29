package com.mathcunha.ppmtool.services;

import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.repositories.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Project saveOrUpdate(Project project){
        log.info("saving "+project.getId());
        project.setIdentifier(project.getIdentifier().toUpperCase());
        return projectRepository.save(project);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Project update(Project project){

        log.info("updating");
        Project persisted =  projectRepository.findByIdentifier(project.getIdentifier());

        if(persisted != null) {
            persisted.setDescription(project.getDescription());
            return projectRepository.save(persisted);
        }else {
            return null;
        }
    }

    public Project findByIdentifier(String id){
        Project pro = projectRepository.findByIdentifier(id);
        if(pro == null){
            return projectRepository.findById(Long.parseLong(id)).orElse(null);
        }
        return null;
    }

    public Iterable<Project> findByDescription(String description){
        return projectRepository.findByDescriptionContaining(description);
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
