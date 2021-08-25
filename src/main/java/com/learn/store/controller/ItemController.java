package com.learn.store.controller;

import com.learn.store.model.ItemColor;
import com.learn.store.repository.ItemColorRepository;
import com.learn.store.repository.ItemRepository;
import com.learn.store.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemColorRepository itemColorRepository;

    @GetMapping(path = "/items")
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @GetMapping(path = "/items/{id}")
    public Item getItem(@PathVariable(value = "id") Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new EntityNotFoundException(itemId + "not found!"));
    }

    @PostMapping(path = "/items")
    public Item addItem(@RequestParam String name, @RequestParam Integer price, @RequestParam String[] colors) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setCreatedAt(new Date());
        item.setUpdatedAt(new Date());

        Set<ItemColor> itemColorSet = new LinkedHashSet<>();
        for (String color : colors) {
            ItemColor itemColor = new ItemColor();
            itemColor.setColor(color);
            itemColor.setCreatedAt(new Date());
            itemColor.setUpdatedAt(new Date());
            itemColor.setItem(item);
            itemColorSet.add(itemColor);
        }
        item.setItemColors(itemColorSet);
        return itemRepository.save(item);
    }

    @PutMapping(path = "/items/{id}")
    public Item updateItem(@PathVariable(value = "id") Long itemId,
                           @RequestParam String name, @RequestParam Integer price) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(itemId + "not found!"));
        item.setName(name);
        item.setPrice(price);
        item.setUpdatedAt(new Date());
        return itemRepository.save(item);
    }

    @DeleteMapping(path = "/items/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(value = "id") Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(itemId + "not found!"));
        itemRepository.delete(item);
        return ResponseEntity.ok().build();
    }
}
