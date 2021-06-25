package com.bizita.tatvademo.prect1;

public class ModelItem {
    public int itemId;
    public int itemIMage;

    public ModelItem(int itemId, int itemImage){
        this.itemId = itemId;
        this.itemIMage = itemImage;
    }
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemIMage() {
        return itemIMage;
    }

    public void setItemIMage(int itemIMage) {
        this.itemIMage = itemIMage;
    }
}
