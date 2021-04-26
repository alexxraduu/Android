package com.example.tema2.models;

public class ItemClass {
    private ItemType itemType;

    public ItemClass(ItemType itemType) {
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
