package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.repository.BaseRepository;
import com.project.listconstructorbackend.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService implements BaseService<ConstructedList> {

    @Autowired
    private ListRepository listRepository;

    @Override
    public BaseRepository<ConstructedList> getRepository() {
        return listRepository;
    }

}
