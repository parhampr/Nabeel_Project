package org.example.Model;

public class Supplier {
    int suppId; String suppName;

    public Supplier(){

    }
    public Supplier(String suppName) {
        this.suppId = (int)(Math.random() * 1000000);
        this.suppName = suppName;
    }

    public int getSuppId() {
        return suppId;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "suppId=" + suppId +
                ", suppName='" + suppName + '\'' +
                '}';
    }
}
