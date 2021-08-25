package com.learn.store.controller;


import com.learn.store.model.Item;
import com.learn.store.model.ItemColor;
import com.learn.store.repository.ItemColorRepository;
import com.learn.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ItemColorController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemColorRepository itemColorRepository;

    @GetMapping(path = "/items/{itemId}/colors")
    public List<ItemColor> getItemColors(@PathVariable(value = "itemId") Long itemId) {
        return itemColorRepository.findByItemItemId(itemId);
    }

    @GetMapping(path = "/items/{itemId}/colors/{id}")
    public ItemColor getItemColor(@PathVariable(value = "id") Long id, @PathVariable(value = "itemId") Long itemId) {
        return itemColorRepository.findByItemColorIdAndItemItemId(id,itemId)
                .orElseThrow(() -> new EntityNotFoundException("color data not found!"));
    }

    @PostMapping(path = "/items/{itemId}/colors")
    public ItemColor addItemColor(@PathVariable(value = "itemId") Long itemId, @RequestParam String color){
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException(itemId + "not found!"));
        ItemColor itemColor = new ItemColor();
        itemColor.setColor(color);
        itemColor.setCreatedAt(new Date());
        itemColor.setUpdatedAt(new Date());
        itemColor.setItem(item);
        return itemColorRepository.save(itemColor);
    }

    @PutMapping(path = "/items/{itemId}/colors/{id}")
    public ItemColor updateItemColor(@PathVariable(value = "id") Long id, @PathVariable(value = "itemId") Long itemId,
                                     @RequestParam String color) {
        ItemColor itemColor = itemColorRepository.findByItemColorIdAndItemItemId(id,itemId)
                .orElseThrow(() -> new EntityNotFoundException("color data not found!"));
        itemColor.setColor(color);
        itemColor.setUpdatedAt(new Date());
        return itemColorRepository.save(itemColor);
    }

    @DeleteMapping(path = "/items/{itemId}/colors/{id}")
    public ResponseEntity<?> deleteItemColor(@PathVariable(value = "id") Long id, @PathVariable(value = "itemId") Long itemId) {
        ItemColor itemColor = itemColorRepository.findByItemColorIdAndItemItemId(id,itemId)
                .orElseThrow(() -> new EntityNotFoundException("color data not found!"));
        itemColorRepository.delete(itemColor);
        return ResponseEntity.ok().build();
    }
}
