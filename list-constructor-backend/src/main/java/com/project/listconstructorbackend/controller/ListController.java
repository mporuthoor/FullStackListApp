package com.project.listconstructorbackend.controller;

import com.google.gson.Gson;
import com.project.listconstructorbackend.exception.ResourceNotFoundException;
import com.project.listconstructorbackend.model.ConstructedList;
import com.project.listconstructorbackend.service.ListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lists")
public class ListController {

    private static final String LIST_NOT_FOUND_ID = "List not found with id: ";
    private static final String LIST_DELETED_ID = "List successfully deleted by id: ";

    @Autowired
    private ListService listService;

    @PostMapping
    public ConstructedList createList(@RequestBody ConstructedList list) {
        return listService.create(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructedList> getListById(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        return listService.getById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(LIST_NOT_FOUND_ID + id));
    }

    @GetMapping("/all")
    public List<ConstructedList> getAllLists() {
        return listService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructedList> updateList(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody ConstructedList list
    ) throws ResourceNotFoundException {

        list.setId(id);

        return listService.update(list).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(LIST_NOT_FOUND_ID + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteList(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        if (listService.delete(id)) {
            return ResponseEntity.ok((new Gson()).toJson(LIST_DELETED_ID + id));
        }

        throw new ResourceNotFoundException(LIST_NOT_FOUND_ID + id);
    }

}
