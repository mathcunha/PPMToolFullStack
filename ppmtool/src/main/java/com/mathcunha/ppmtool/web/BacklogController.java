package com.mathcunha.ppmtool.web;

import com.mathcunha.ppmtool.domain.Task;
import com.mathcunha.ppmtool.services.MapValidationErrorService;
import com.mathcunha.ppmtool.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/backlog")
public class BacklogController {

    private final TaskService taskService;
    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public BacklogController(TaskService taskService, MapValidationErrorService mapValidationErrorService) {
        this.taskService = taskService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("/{identifier}")
    public ResponseEntity<?> addTask(@Valid @RequestBody Task task, @PathVariable String identifier, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;

        Task createdTask = taskService.addTask(identifier, task);

        if(createdTask == null) {
            Map<String, String> errors = new HashMap<>(1);
            errors.put("ProjectNotFound", String.format("Project with id %s was not found", identifier));
            return new ResponseEntity<Map<String, String>>(errors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Iterable<Task>> getBacklog(@PathVariable String identifier){
        return new ResponseEntity<Iterable<Task>>(taskService.findByIdentifier(identifier), HttpStatus.OK);
    }

    @GetMapping("/{identifier}/{sequence}")
    public ResponseEntity<?> getTask(@PathVariable String identifier, @PathVariable String sequence){
        Task task = taskService.findBySequence(sequence);

        if(task == null) {
            Map<String, String> errors = new HashMap<>(1);
            errors.put("TaskNotFound", String.format("Task with sequence %s was not found", sequence));
            return new ResponseEntity<Map<String, String>>(errors, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }
}
