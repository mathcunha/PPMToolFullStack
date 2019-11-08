package com.mathcunha.ppmtool.services;

import com.mathcunha.ppmtool.domain.Backlog;
import com.mathcunha.ppmtool.domain.Task;
import com.mathcunha.ppmtool.repositories.BacklogRepository;
import com.mathcunha.ppmtool.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final BacklogRepository backlogRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, BacklogRepository backlogRepository) {
        this.taskRepository = taskRepository;
        this.backlogRepository = backlogRepository;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Task addTask(String identifier, Task task){
        Backlog backlog = backlogRepository.findByIdentifier(identifier);
        if(backlog == null){
            return null;
        }

        //new task
        if(task.getId() == null) {
            task.setBacklog(backlog);
            Integer sequence = backlog.getSequence();
            backlog.setSequence(++sequence);
            task.setSequence(String.format("%s-%d", identifier, sequence));
            task.setIdentifier(identifier);
        }

        if(task.getPriority() == null || task.getPriority() == 0){
            task.setPriority(3);
        }

        if(task.getStatus() == null || task.getStatus().isBlank()){
            task.setStatus("TO_DO");
        }

        return taskRepository.save(task);
    }

    public Iterable<Task> findByIdentifier(String identifier){
        return taskRepository.findByIdentifierOrderByPriority(identifier);
    }

    public Task findBySequence(String sequence){
        return taskRepository.findBySequence(sequence);
    }
}
