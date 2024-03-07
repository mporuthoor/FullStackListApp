package com.project.listconstructorbackend.repository;

import com.project.listconstructorbackend.model.ConstructedList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ListRepository extends BaseRepository<ConstructedList> {

    @Query("SELECT t FROM ConstructedList t WHERE t.name =:name")
    Optional<ConstructedList> findListByName(@Param("name") String name);
}
