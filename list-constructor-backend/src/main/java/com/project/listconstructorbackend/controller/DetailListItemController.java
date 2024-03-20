package com.project.listconstructorbackend.controller;

import com.google.gson.Gson;
import com.project.listconstructorbackend.exception.InvalidPayloadException;
import com.project.listconstructorbackend.exception.ResourceNotFoundException;
import com.project.listconstructorbackend.model.DetailListItem;
import com.project.listconstructorbackend.service.DetailListItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/detailListItems")
public class DetailListItemController {

    private static final String DETAIL_LIST_ITEM_NOT_FOUND_ID = "Detail list item not found with id: ";
    private static final String DETAIL_LIST_ITEM_DELETED_ID = "Detail list item successfully deleted by id: ";

    @Autowired
    private DetailListItemService detailListItemService;

    @PostMapping
    public DetailListItem createDetailListItem(@RequestBody DetailListItem detailListItem)
            throws InvalidPayloadException, ResourceNotFoundException {
        return detailListItemService.create(detailListItem);
    }

    @PostMapping("/batch")
    public List<DetailListItem> createDetailListItems(@RequestBody List<DetailListItem> detailListItems)
            throws InvalidPayloadException, ResourceNotFoundException {
        return detailListItemService.createMultiple(detailListItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailListItem> getDetailListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        return detailListItemService.getById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(DETAIL_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @GetMapping("/list/{listId}")
    public List<DetailListItem> getDetailListItemsByListId(@PathVariable(value = "listId") UUID listId)
            throws ResourceNotFoundException {
        return detailListItemService.getItemsByListId(listId);
    }

    @GetMapping("/all")
    public List<DetailListItem> getAllDetailListItems() {
        return detailListItemService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetailListItem> updateDetailListItem(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody DetailListItem detailListItem
    ) throws ResourceNotFoundException {

        detailListItem.setId(id);

        return detailListItemService.update(detailListItem).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(DETAIL_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDetailListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        if (detailListItemService.delete(id)) {
            return ResponseEntity.ok((new Gson()).toJson(DETAIL_LIST_ITEM_DELETED_ID + id));
        }

        throw new ResourceNotFoundException(DETAIL_LIST_ITEM_NOT_FOUND_ID + id);
    }

}
