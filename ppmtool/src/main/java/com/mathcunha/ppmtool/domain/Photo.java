package com.mathcunha.ppmtool.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@NamedQueries(value = {@NamedQuery(name = "Photo.findPhotoCount", query = "select new com.mathcunha.ppmtool.domain.PhotoStatistics(p.owner, count(p.id)) from Photo p group by owner")})
@NamedNativeQueries(value = {@NamedNativeQuery(name = "Photo.findMaxPhotoCount", query = "select p.owner, count(p.id) as total from photo p group by owner order by total desc limit 1")})
public class Photo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Photo title is required")
    private String title;
    @NotBlank(message = "Project description is required")
    private String description;
    @NotBlank(message = "Photo owner is required")
    @Size(max=64, message = "Please no more than 64 characters")
    private String owner;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date updatedDate;

    public Photo() {
    }

    public Photo(@NotBlank(message = "Photo title is required") String title, @NotBlank(message = "Project description is required") String description, @NotBlank(message = "Photo owner is required") @Size(max = 64, message = "Please no more than 64 characters") String owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


}
