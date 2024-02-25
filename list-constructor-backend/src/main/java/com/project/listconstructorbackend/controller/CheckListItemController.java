package com.project.listconstructorbackend.controller;

import com.project.listconstructorbackend.exception.ResourceNotFoundException;
import com.project.listconstructorbackend.model.CheckListItem;
import com.project.listconstructorbackend.service.CheckListItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/checkListItems")
public class CheckListItemController {

    private static final String CHECK_LIST_ITEM_NOT_FOUND_ID = "Check list item not found for id: ";

    @Autowired
    private CheckListItemService checkListItemService;

    @PostMapping
    public CheckListItem createCheckListItem(@RequestBody CheckListItem checkListItem) {
        return checkListItemService.create(checkListItem);
    }

    @PostMapping("/batch")
    public List<CheckListItem> createCheckListItems(@RequestBody List<CheckListItem> checkListItems) {
        return checkListItemService.createMultiple(checkListItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckListItem> getCheckListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        return checkListItemService.get(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(CHECK_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @GetMapping("/list/{listId}")
    public List<CheckListItem> getCheckListItemsByListId(@PathVariable(value = "listId") UUID listId)
            throws ResourceNotFoundException {

        return checkListItemService.getByListId(listId);
    }

    @GetMapping
    public List<CheckListItem> getAllCheckListItems() {
        return checkListItemService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckListItem> updateCheckListItem(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody CheckListItem checkListItem
    ) throws ResourceNotFoundException {

        if (checkListItem.getId() == null) {
            checkListItem.setId(id);
        }

        return checkListItemService.update(checkListItem).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(CHECK_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        if (checkListItemService.delete(id)) {
            return ResponseEntity.noContent().build();
        }

        throw new ResourceNotFoundException(CHECK_LIST_ITEM_NOT_FOUND_ID + id);
    }

}
