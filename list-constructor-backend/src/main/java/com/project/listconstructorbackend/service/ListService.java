package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.model.ConstructedListType;
import com.project.listconstructorbackend.model.ListOrder;
import com.project.listconstructorbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ListService implements BaseService<ConstructedList> {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ListOrderRepository listOrderRepository;

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
        ConstructedList savedList = listRepository.save(list);

        ListOrder listOrder = new ListOrder(savedList.getId(), (short) listOrderRepository.count());
        listOrderRepository.save(listOrder);

        return savedList;
    }

    public List<ConstructedList> getAll() {
        List<UUID> orderedListIds = listOrderRepository.findAllByOrderByIndexAsc().stream()
                .map((ListOrder::getListId)).collect(Collectors.toCollection(ArrayList::new));
        HashMap<UUID, ConstructedList> savedLists = (HashMap<UUID, ConstructedList>) listRepository.findAll().stream()
                .collect(Collectors.toMap(ConstructedList::getId, Function.identity()));

        return orderedListIds.stream().map(savedLists::get).collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<ConstructedList> update(ConstructedList list) {
        Optional<ConstructedList> saved = listRepository.findById(list.getId());

        // do not update type
        return saved.map(savedList -> {
            savedList.setName(list.getName());
            savedList.setDescription(list.getDescription());
            savedList.setItemIds(list.getItemIds());

            return listRepository.save(savedList);
        });
    }

    public boolean delete(UUID id) {
        Optional<ConstructedList> constructedList = getById(id);
        if (constructedList.isEmpty()) {
            return false;
        }

        List<UUID> itemIds = constructedList.get().getItemIds();
        ConstructedListType type = constructedList.get().getType();

        listRepository.deleteById(id);
        deleteAndUpdateOrder(id);
        switch (type) {
            case CHECK -> checkListItemRepository.deleteAllById(itemIds);
            case DETAIL -> detailListItemRepository.deleteAllById(itemIds);
            case RANK -> rankListItemRepository.deleteAllById(itemIds);
        }

        return true;
    }

    private void deleteAndUpdateOrder(UUID id) {
        Optional<ListOrder> savedOrder = listOrderRepository.findById(id);
        if (savedOrder.isEmpty()) {
            return;
        }

        int start = savedOrder.get().getIndex() + 1;
        int end = (int) listOrderRepository.count();
        List<ListOrder> ordersToUpdate = listOrderRepository.findAllByOrderByIndexAsc().subList(start, end);

        for (ListOrder listOrder : ordersToUpdate) {
            listOrder.setIndex((short) (listOrder.getIndex() - 1));
        }

        listOrderRepository.deleteById(id);
        listOrderRepository.saveAll(ordersToUpdate);
    }

}
