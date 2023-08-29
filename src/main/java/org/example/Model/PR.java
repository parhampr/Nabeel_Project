package org.example.Model;

public class PR {
    int prID, suppId, itemId, quantityRequired;

    public PR(){

    }

    public PR(int suppId, int itemId, int quantityRequired) {
        this.prID = (int)(Math.random() * 1000000);;
        this.suppId = suppId;
        this.itemId = itemId;
        this.quantityRequired = quantityRequired;
    }

    public int getPrID() {
        return prID;
    }

    public int getSuppId() {
        return suppId;
    }

    public void setSuppId(int suppId) {
        this.suppId = suppId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    @Override
    public String toString() {
        return "PR{" +
                "prID=" + prID +
                ", suppId=" + suppId +
                ", itemId=" + itemId +
                ", quantityRequired=" + quantityRequired +
                '}';
    }
}
