package com.learn.store.repository;

import com.learn.store.model.Item;
import com.learn.store.model.ItemColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemColorRepository extends JpaRepository<ItemColor, Long> {
    List<ItemColor> findByItemItemId(Long itemId);
    Optional<ItemColor> findByItemColorIdAndItemItemId(Long id, Long itemId);
}
