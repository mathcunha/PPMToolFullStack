package com.mathcunha.ppmtool.services;

import com.mathcunha.ppmtool.domain.Backlog;
import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.repositories.BacklogRepository;
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
    private final BacklogRepository backlogRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Project saveOrUpdate(Project project){
        log.info("saving "+project.getId());
        Boolean isNew = project.getId() == null;

        project.setIdentifier(project.getIdentifier().toUpperCase());
        Project persistedProject =  projectRepository.save(project);

        if(isNew){
            Backlog backlog = new Backlog(persistedProject);
            log.info(backlog.toString());
            backlogRepository.save(backlog);
        }

        return persistedProject;
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
        return projectRepository.findByIdentifier(id);
    }

    public Iterable<Project> findAll(){
        return projectRepository.findAll();
    }

    public Boolean deleteByIdentifier(String id){
        Project project = findByIdentifier(id);
        if (project != null) {
            Backlog backlog = new Backlog();
            backlog.setId(project.getId());
            backlogRepository.delete(backlog);
            projectRepository.delete(project);
            return true;
        }
        return false;
    }
}
