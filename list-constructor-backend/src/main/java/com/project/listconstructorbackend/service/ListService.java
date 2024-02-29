package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.model.ConstructedListType;
import com.project.listconstructorbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ListService implements BaseService<ConstructedList> {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private CheckListItemRepository checkListItemRepository;
    @Autowired
    private DetailListItemRepository detailListItemRepository;
    @Autowired
    private RankListItemRepository rankListItemRepository;

    @Override
    public BaseRepository<ConstructedList> getRepository() {
        return listRepository;
    }

    public ConstructedList create(ConstructedList list) {
        return getRepository().save(list);
    }

    public Optional<ConstructedList> update(ConstructedList list) {
        Optional<ConstructedList> savedList = getRepository().findById(list.getId());

        // only update saved name and description
        return savedList.map(constructedList -> {
            constructedList.setName(list.getName());
            constructedList.setDescription(list.getDescription());

            return getRepository().save(constructedList);
        });
    }

    public boolean delete(UUID id) {
        Optional<ConstructedList> constructedList = get(id);

        if (constructedList.isPresent()) {
            List<UUID> itemIds = constructedList.get().getItemIds();
            ConstructedListType type = constructedList.get().getType();

            getRepository().deleteById(id);
            switch (type) {
                case CHECK -> checkListItemRepository.deleteAllById(itemIds);
                case DETAIL -> detailListItemRepository.deleteAllById(itemIds);
                case RANK -> rankListItemRepository.deleteAllById(itemIds);
            }

            return true;
        }

        return false;
    }

}
