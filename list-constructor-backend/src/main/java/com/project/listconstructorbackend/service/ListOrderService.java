package com.project.listconstructorbackend.service;

import com.project.listconstructorbackend.exception.InvalidPayloadException;
import com.project.listconstructorbackend.exception.ResourceNotFoundException;
import com.project.listconstructorbackend.model.ListOrder;
import com.project.listconstructorbackend.repository.ListOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ListOrderService {

    private static final String CANNOT_UPDATE_WRONG_NUMBER_OF_IDS =
            "Cannot update order because number of ids provided does not match current number of lists.";

    private static final String CANNOT_UPDATE_LIST_NOT_FOUND =
            "Cannot update order because because list was not found with id: ";

    @Autowired
    private ListOrderRepository listOrderRepository;

    public List<ListOrder> updateOrder(List<UUID> listIds) throws InvalidPayloadException, ResourceNotFoundException {
        if (listIds.size() != listOrderRepository.count()) {
            throw new InvalidPayloadException(CANNOT_UPDATE_WRONG_NUMBER_OF_IDS);
        }

        HashMap<UUID, ListOrder> savedMap = (HashMap<UUID, ListOrder>) listOrderRepository.findAll().stream()
                        .collect(Collectors.toMap(ListOrder::getListId, Function.identity()));
        List<ListOrder> updatedOrders = new ArrayList<>();

        short count = 0;
        for (UUID listId : listIds) {
            if (!savedMap.containsKey(listId)) {
                throw new ResourceNotFoundException(CANNOT_UPDATE_LIST_NOT_FOUND + listId);
            }

            ListOrder updatedOrder = savedMap.get(listId);
            updatedOrder.setIndex(count);
            updatedOrders.add(updatedOrder);
            count++;
        }
        listOrderRepository.saveAll(updatedOrders);

        return listOrderRepository.findAllByOrderByIndexAsc();
    }

}
