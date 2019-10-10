package com.mathcunha.ppmtool.web;

import com.mathcunha.ppmtool.domain.Project;
import com.mathcunha.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping()
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            result.getFieldErrors().parallelStream().forEach(fieldError -> {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            });

            return new ResponseEntity<Map<String, String>> (errorMap, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Project>(projectService.saveOrUpdate(project), HttpStatus.CREATED);
    }

}
