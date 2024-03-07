package com.project.listconstructorbackend.controller;

import com.project.listconstructorbackend.exception.ResourceNotFoundException;
import com.project.listconstructorbackend.model.RankListItem;
import com.project.listconstructorbackend.service.RankListItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rankListItems")
public class RankListItemController {

    private static final String RANK_LIST_ITEM_NOT_FOUND_ID = "Rank list item not found with id: ";
    private static final String RANK_LIST_ITEM_DELETED_ID = "Rank list item successfully deleted by id: ";

    @Autowired
    private RankListItemService rankListItemService;

    @PostMapping
    public RankListItem createRankListItem(@RequestBody RankListItem rankListItem)
            throws ResourceNotFoundException {
        return rankListItemService.create(rankListItem);
    }

    @PostMapping("/batch")
    public List<RankListItem> createRankListItems(@RequestBody List<RankListItem> rankListItems)
            throws ResourceNotFoundException {
        return rankListItemService.createMultiple(rankListItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RankListItem> getRankListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        return rankListItemService.getById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(RANK_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @GetMapping("/list/{listId}")
    public List<RankListItem> getRankListItemsByListId(@PathVariable(value = "listId") UUID listId)
            throws ResourceNotFoundException {

        return rankListItemService.getItemsByListId(listId);
    }

    @GetMapping("/all")
    public List<RankListItem> getAllRankListItems() {
        return rankListItemService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RankListItem> updateRankListItem(
            @PathVariable(value = "id") UUID id,
            @Valid @RequestBody RankListItem rankListItem
    ) throws ResourceNotFoundException {

        rankListItem.setId(id);

        return rankListItemService.update(rankListItem).map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(RANK_LIST_ITEM_NOT_FOUND_ID + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRankListItem(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {

        if (rankListItemService.delete(id)) {
            return ResponseEntity.ok(RANK_LIST_ITEM_DELETED_ID + id);
        }

        throw new ResourceNotFoundException(RANK_LIST_ITEM_NOT_FOUND_ID + id);
    }

}
