package com.mathcunha.ppmtool.repositories;

import com.mathcunha.ppmtool.domain.Photo;
import com.mathcunha.ppmtool.domain.PhotoStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    List<PhotoStatistics> findPhotoCount();
    PhotoStatistics findMaxPhotoCount();
}
