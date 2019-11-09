package com.mathcunha.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"identifier"}, name = "UK_backlog_identifier")})
public class Backlog {
    @Id
    private Long id;
    private Integer sequence = 0;
    @Size(min = 4, max=5, message = "Please use 4 to 5 characters")
    @Column(updatable = false, unique = true)
    private String identifier;
    @OneToMany(mappedBy = "backlog")
    @JsonIgnore
    private List<Task> tasks;
    @OneToOne(fetch = FetchType.EAGER)
    @NotNull(message = "Project is required")
    @MapsId
    private Project project;

    public Backlog() {
    }

    public Backlog(Project project) {
        this.project = project;
        this.identifier = project.getIdentifier();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Backlog{" +
                "id=" + id +
                ", sequence=" + sequence +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
