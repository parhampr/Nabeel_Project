package org.example.Model;

import java.util.Date;

public class ItemSales {
    int saleId, itemId, unitsSold;
    Date date;

    public ItemSales(){

    }

    public ItemSales(int itemId, int unitsSold) {
        this.saleId = (int)(Math.random() * 1000000);;
        this.itemId = itemId;
        this.unitsSold = unitsSold;
        this.date = new Date();
    }

    public int getSaleId() {
        return saleId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUnitsSold() {
        return unitsSold;
    }

    public void setUnitsSold(int unitsSold) {
        this.unitsSold = unitsSold;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ItemSales{" +
                "saleId=" + saleId +
                ", itemId=" + itemId +
                ", unitsSold=" + unitsSold +
                ", date=" + date +
                '}';
    }
}
