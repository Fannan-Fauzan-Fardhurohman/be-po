package com.fanxan.bepo.models.service;

import com.fanxan.bepo.models.entity.Items;
import com.fanxan.bepo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public List<Items> getAllItems() {
        logger.info("Fetching all items");
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Items> getItemById(Integer id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public Items createItem(Items item) {
        Assert.notNull(item, "Item must not be null");
        Assert.hasText(item.getName(), "Item name must not be empty");

        logger.info("Creating new item: {}", item.getName());

        // Validate price and cost
        if (item.getPrice() == null || item.getPrice() < 0) {
            throw new IllegalArgumentException("Item price is not valid");
        }
        if (item.getCost() == null || item.getCost() < 0) {
            throw new IllegalArgumentException("Item cost is not valid");
        }

        Items savedItem = itemRepository.save(item);
        logger.info("Item created successfully with id: {}", savedItem.getId());
        return savedItem;
    }

    @Transactional
    public Optional<Items> updateItem(Integer id, Items itemDetails) {
        Assert.notNull(id, "Item ID must not be null");
        Assert.notNull(itemDetails, "Item details must not be null");

        logger.info("Updating item with id: {}", id);

        return itemRepository.findById(id).map(existingItem -> {
            if (itemDetails.getName() != null && !itemDetails.getName().trim().isEmpty()) {
                existingItem.setName(itemDetails.getName());
            }

            existingItem.setDescription(itemDetails.getDescription());

            if (itemDetails.getPrice() != null && itemDetails.getPrice() >= 0) {
                existingItem.setPrice(itemDetails.getPrice());
            }

            if (itemDetails.getCost() != null && itemDetails.getCost() >= 0) {
                existingItem.setCost(itemDetails.getCost());
            }

            Items updatedItem = itemRepository.save(existingItem);
            logger.info("Item updated successfully with id: {}", id);
            return updatedItem;
        });
    }

    @Transactional
    public boolean deleteItem(Integer id) {
        Assert.notNull(id, "Item ID must not be null");
        logger.info("Deleting item with id: {}", id);

        return itemRepository.findById(id).map(item -> {
            itemRepository.delete(item);
            logger.info("Item deleted successfully with id: {}", id);
            return true;
        }).orElse(false);
    }
}
