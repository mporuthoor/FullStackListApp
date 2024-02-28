package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.RankListItem;
import com.project.listconstructorbackend.repository.BaseRepository;
import com.project.listconstructorbackend.repository.ListRepository;
import com.project.listconstructorbackend.repository.RankListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankListItemService implements ListItemService<RankListItem> {

    @Autowired
    private RankListItemRepository rankListItemRepository;

    @Autowired
    private ListRepository listRepository;

    @Override
    public BaseRepository<RankListItem> getRepository() {
        return rankListItemRepository;
    }

    @Override
    public ListRepository getListRepository() {
        return listRepository;
    }

}
