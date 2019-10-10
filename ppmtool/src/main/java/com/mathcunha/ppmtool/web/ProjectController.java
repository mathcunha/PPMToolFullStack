package com.mathcunha.ppmtool.web;

import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.services.MapValidationErrorService;
import com.mathcunha.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    private final ProjectService projectService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public ProjectController(ProjectService projectService, MapValidationErrorService mapValidationErrorService) {
        this.projectService = projectService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping()
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<Project>(projectService.saveOrUpdate(project), HttpStatus.CREATED);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Project> findByIdentifier(@PathVariable String identifier){
        Project project = projectService.findByIdentifier(identifier);
        if (project == null){
            project = new Project();
            project.setIdentifier(identifier);
            return new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<Project>> findAll(){
        return new ResponseEntity<Iterable<Project>>(projectService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String identifier){
        if (projectService.deleteByIdentifier(identifier)) {
            return new ResponseEntity<String>(String.format("Project with id %s was deleted", identifier), HttpStatus.OK);
        }else{
            return new ResponseEntity<String>(String.format("Project with id %s was not found", identifier), HttpStatus.NOT_FOUND);
        }
    }
}
