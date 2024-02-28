package com.project.listconstructorbackend.repository;

import com.project.listconstructorbackend.model.ListItemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@NoRepositoryBean
public interface ListItemRepository<T extends ListItemEntity> extends BaseRepository<T>{

    @Query("SELECT t FROM #{#entityName} t WHERE t.listId =:listId")
    List<T> getItemsByListId(@Param("listId") UUID listId);

}
