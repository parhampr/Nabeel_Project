package org.example.Model;

public class Item {
    int itemID, suppId, quantityAvailable; String itemName;

    public Item(){

    }
    public Item(int suppId, String itemName, int quantityAvailable) {
        this.itemID = (int)(Math.random() * 1000000);
        this.suppId = suppId;
        this.quantityAvailable = quantityAvailable;
        this.itemName = itemName;
    }

    public int getItemID() {
        return itemID;
    }

    public int getSuppId() {
        return suppId;
    }

    public void setSuppId(int suppId) {
        this.suppId = suppId;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", suppId=" + suppId +
                ", quantityAvailable=" + quantityAvailable +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
