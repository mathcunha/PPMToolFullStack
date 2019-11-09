package com.mathcunha.ppmtool.repositories;

import com.mathcunha.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotBlank;

public interface BacklogRepository extends CrudRepository<Backlog, Long> {
    Backlog findByIdentifier(@NotBlank String identifier);
}
