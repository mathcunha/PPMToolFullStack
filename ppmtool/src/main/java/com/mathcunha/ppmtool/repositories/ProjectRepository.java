package com.mathcunha.ppmtool.repositories;

import com.mathcunha.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Null
    Project findByIdentifier(String identifier);
}
