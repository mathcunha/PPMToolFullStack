package com.mathcunha.ppmtool.repositories;

import com.mathcunha.ppmtool.domain.Task;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Task, Long> {
    Iterable<Task> findByIdentifierOrderByPriority(String identifier);
    Task findBySequence(String sequence);
}
