package com.project.listconstructorbackend.controller;

import com.project.listconstructorbackend.exception.InvalidPayloadException;
import com.project.listconstructorbackend.exception.ResourceNotFoundException;
import com.project.listconstructorbackend.model.ListOrder;
import com.project.listconstructorbackend.service.ListOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/listOrder")
public class ListOrderController {

    @Autowired
    private ListOrderService listOrderService;

    @PutMapping
    public List<ListOrder> updateListOrder(@RequestBody List<UUID> listIds)
            throws InvalidPayloadException, ResourceNotFoundException {

        return listOrderService.updateOrder(listIds);
    }

}
