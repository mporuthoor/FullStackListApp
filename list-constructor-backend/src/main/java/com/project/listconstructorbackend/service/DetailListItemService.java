package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.DetailListItem;
import com.project.listconstructorbackend.repository.BaseRepository;
import com.project.listconstructorbackend.repository.DetailListItemRepository;
import com.project.listconstructorbackend.repository.ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailListItemService implements ListItemService<DetailListItem> {

    @Autowired
    private DetailListItemRepository detailListItemRepository;

    @Autowired
    private ListRepository listRepository;

    @Override
    public BaseRepository<DetailListItem> getRepository() {
        return detailListItemRepository;
    }

    @Override
    public ListRepository getListRepository() {
        return listRepository;
    }

}
