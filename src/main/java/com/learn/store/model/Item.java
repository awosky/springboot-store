package com.learn.store.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.core.SpringVersion;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "item_id")
    private Integer itemId;

    private String name;

    private Integer price;

    private Date createdAt;

    private Date updatedAt;

    public Integer getId() {
        return itemId;
    }

    public void setId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
