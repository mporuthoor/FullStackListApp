package com.project.listconstructorbackend.controller;

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
@RequestMapping("/api/v1/lists")
public class ListController {

    private static final String LIST_NOT_FOUND_ID = "List not found for id: ";
    private static final String LIST_DELETED_ID = "List successfully deleted by id: ";

    @Autowired
    private ListService listService;

    @PostMapping
    public ConstructedList createList(@RequestBody ConstructedList list) {
        return this.listService.create(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConstructedList> getList(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        return listService.get(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(LIST_NOT_FOUND_ID + id));
    }

    @GetMapping
    public List<ConstructedList> getAllLists() {
        return this.listService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConstructedList> updateList(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody ConstructedList list
    ) throws ResourceNotFoundException {

        list.setId(id);

        return this.listService.update(list).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(LIST_NOT_FOUND_ID + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteList(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        if (this.listService.delete(id)) {
            return ResponseEntity.ok(LIST_DELETED_ID + id);
        }

        throw new ResourceNotFoundException(LIST_NOT_FOUND_ID + id);
    }

}
