package com.project.listconstructorbackend.controller;

import com.google.gson.Gson;
import com.project.listconstructorbackend.exception.InvalidPayloadException;
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
@RequestMapping("/checkListItems")
public class CheckListItemController {

    private static final String CHECK_LIST_ITEM_NOT_FOUND_ID = "Check list item not found with id: ";
    private static final String CHECK_LIST_ITEM_DELETED_ID = "Check list item successfully deleted by id: ";

    @Autowired
    private CheckListItemService checkListItemService;

    @PostMapping
    public CheckListItem createCheckListItem(@RequestBody CheckListItem checkListItem)
            throws InvalidPayloadException, ResourceNotFoundException {
        return checkListItemService.create(checkListItem);
    }

    @PostMapping("/batch")
    public List<CheckListItem> createCheckListItems(@RequestBody List<CheckListItem> checkListItems)
            throws InvalidPayloadException, ResourceNotFoundException {
        return checkListItemService.createMultiple(checkListItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckListItem> getCheckListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        return checkListItemService.getById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(CHECK_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @GetMapping("/list/{listId}")
    public List<CheckListItem> getCheckListItemsByListId(@PathVariable(value = "listId") UUID listId)
            throws ResourceNotFoundException {
        return checkListItemService.getItemsByListId(listId);
    }

    @GetMapping("/all")
    public List<CheckListItem> getAllCheckListItems() {
        return checkListItemService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CheckListItem> updateCheckListItem(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody CheckListItem checkListItem
    ) throws ResourceNotFoundException {

        checkListItem.setId(id);

        return checkListItemService.update(checkListItem).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(CHECK_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCheckListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        if (checkListItemService.delete(id)) {
            return ResponseEntity.ok((new Gson()).toJson(CHECK_LIST_ITEM_DELETED_ID + id));
        }

        throw new ResourceNotFoundException(CHECK_LIST_ITEM_NOT_FOUND_ID + id);
    }

}
