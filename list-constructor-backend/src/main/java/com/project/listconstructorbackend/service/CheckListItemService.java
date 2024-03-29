package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.CheckListItem;
import com.project.listconstructorbackend.repository.BaseRepository;
import com.project.listconstructorbackend.repository.CheckListItemRepository;
import com.project.listconstructorbackend.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckListItemService implements ListItemService<CheckListItem> {

    @Autowired
    private CheckListItemRepository checkListItemRepository;

    @Autowired
    private ListRepository listRepository;

    @Override
    public BaseRepository<CheckListItem> getRepository() {
        return checkListItemRepository;
    }

    @Override
    public ListRepository getListRepository() {
        return listRepository;
    }

}
