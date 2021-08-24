package com.learn.store.repository;

import com.learn.store.model.Item;
import com.learn.store.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Integer> {
}
