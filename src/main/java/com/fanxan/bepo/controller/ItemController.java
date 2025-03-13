package com.fanxan.bepo.controller;

import com.fanxan.bepo.exception.ResourceNotFoundException;
import com.fanxan.bepo.models.WebResponse;
import com.fanxan.bepo.models.entity.Items;
import com.fanxan.bepo.models.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping
    public ResponseEntity<WebResponse<List<Items>>> getAllItems() {
        logger.info("REST request to get all items");
        List<Items> items = itemService.getAllItems();
        return ResponseEntity.ok(new WebResponse<>(true, "Success", items));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebResponse<Items>> getItemById(@PathVariable Integer id) {
        logger.info("REST request to get item with id: {}", id);
        Items item = itemService.getItemById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        return ResponseEntity.ok(new WebResponse<>(true, "Success", item));
    }

    @PostMapping
    public ResponseEntity<WebResponse<Items>> createItem(@RequestBody @Validated Items item) {
        logger.info("REST request to create a new item");
        try {
            Items createdItem = itemService.createItem(item);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new WebResponse<>(true, "Item created successfully", createdItem));
        } catch (IllegalArgumentException e) {
            logger.error("Error creating item: {}", e.getMessage());
            throw e;
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<WebResponse<Items>> updateItem(@PathVariable Integer id, @RequestBody @Validated Items itemDetails) {
        logger.info("REST request to update item with id: {}", id);
        Items updatedItem = itemService.updateItem(id, itemDetails)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
        return ResponseEntity.ok(new WebResponse<>(true, "Item updated successfully", updatedItem));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<WebResponse<Void>> deleteItem(@PathVariable Integer id) {
        logger.info("REST request to delete item with id: {}", id);
        if (!itemService.deleteItem(id)) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        return ResponseEntity.ok(new WebResponse<>(true, "Item deleted successfully", null));
    }
}