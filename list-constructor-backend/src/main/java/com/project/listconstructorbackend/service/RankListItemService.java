package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.RankListItem;
import com.project.listconstructorbackend.repository.BaseRepository;
import com.project.listconstructorbackend.repository.RankListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RankListItemService implements ListItemService<RankListItem> {

    @Autowired
    private RankListItemRepository rankListItemRepository;

    @Override
    public BaseRepository<RankListItem> getRepository() {
        return rankListItemRepository;
    }

}
