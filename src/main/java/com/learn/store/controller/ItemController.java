package com.learn.store.controller;

import com.learn.store.repository.ItemRepository;
import com.learn.store.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path="/api")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(path="/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping(path="/items/{id}")
    public Item getItem(@PathVariable(value = "id") Integer itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException(itemId + "not found!"));
    }

    @PostMapping(path="/items")
    public Item addItem (@RequestParam String name, @RequestParam Integer price) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setCreatedAt(new Date());
        item.setUpdatedAt(new Date());
        return itemRepository.save(item);
    }

    @PutMapping(path="/items/{id}")
    public Item updateItem(@PathVariable(value = "id") Integer itemId,
                           @RequestParam String name, @RequestParam Integer price) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(itemId + "not found!"));
        item.setName(name);
        item.setPrice(price);
        return itemRepository.save(item);
    }

    @DeleteMapping(path="/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Integer itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(itemId + "not found!"));
        itemRepository.delete(item);
        return ResponseEntity.ok().build();
    }
}
