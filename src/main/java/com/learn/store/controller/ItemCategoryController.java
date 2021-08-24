package com.learn.store.controller;

import com.learn.store.model.ItemCategory;
import com.learn.store.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/api")
public class ItemCategoryController {
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @GetMapping(path="/categories")
    public List<ItemCategory> getItemCategories() {
        return itemCategoryRepository.findAll();
    }

    @GetMapping(path="/categories/{id}")
    public ItemCategory getItemCategory(@PathVariable(value = "id") Integer itemCategoryId) {
        return itemCategoryRepository.findById(itemCategoryId).orElseThrow(() -> new EntityNotFoundException(itemCategoryId + "not found!"));
    }

    @PostMapping(path="/categories")
    public ItemCategory addItemCategory (@RequestParam String name) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setName(name);
        itemCategory.setCreatedAt(new Date());
        itemCategory.setUpdatedAt(new Date());
        return itemCategoryRepository.save(itemCategory);
    }

    @PutMapping(path="/categories/{id}")
    public ItemCategory updateItemCategory(@PathVariable(value = "id") Integer itemCategoryId,
                           @RequestParam String name) {
        ItemCategory item = itemCategoryRepository.findById(itemCategoryId)
                .orElseThrow(() -> new EntityNotFoundException(itemCategoryId + "not found!"));
        item.setName(name);
        return itemCategoryRepository.save(item);
    }

    @DeleteMapping(path="/categories/{id}")
    public ResponseEntity<?> deleteItemCategory(@PathVariable(value = "id") Integer itemCategoryId) {
        ItemCategory item = itemCategoryRepository.findById(itemCategoryId)
                .orElseThrow(() -> new EntityNotFoundException(itemCategoryId + "not found!"));
        itemCategoryRepository.delete(item);
        return ResponseEntity.ok().build();
    }
}
