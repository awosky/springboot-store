package com.learn.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ItemColor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_color_id")
    private Long itemColorId;

    private String color;

    private Date createdAt;

    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", nullable=false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Item item;

    public Long getItemColorId() {
        return itemColorId;
    }

    public void setItemColorId(Long itemColorId) {
        this.itemColorId = itemColorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
