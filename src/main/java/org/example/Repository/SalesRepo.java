package org.example.Repository;

import org.example.Model.ItemSales;
import org.example.Model.User;
import org.example.Utils.Constant;
import org.example.Utils.Persistance;

import java.util.ArrayList;
import java.util.List;

public class SalesRepo {
    List<ItemSales> salesList;

    private final User currentUser;

    Persistance persistance = new Persistance();

    public SalesRepo(User user){
        salesList = persistance.getSalesList();
        this.currentUser = user;
    }

    private void checkAccess(String functionality) throws IllegalAccessException {
        // REFRESH DATA
        salesList = persistance.getSalesList();
        Constant.checkAccess(List.of("SaleEntry", "SaleDelete", "SaleEdit"), List.of(), functionality, currentUser);
    }

    public ItemSales addSale(ItemSales sales){
        try {
            checkAccess("SaleEntry");
        } catch (IllegalAccessException ignored) {
            return null;
        }

        salesList.add(sales);
        persistance.updateData(null, null, null, null, salesList);
        return sales;
    }

    public void deleteSupplier(int saleId){
        try {
            checkAccess("SaleDelete");
        } catch (IllegalAccessException ignored) {
            return;
        }

        // Delete Supplier and his items altogether
        salesList.removeIf(sales -> saleId == sales.getSaleId());
        persistance.updateData(null, null, null, null, salesList);
    }

    public ItemSales suppEdit(ItemSales editedSale){
        try {
            checkAccess("SuppEdit");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        for(ItemSales sale: salesList){
            if(sale.getSaleId() == editedSale.getSaleId()){
                sale.setItemId(editedSale.getItemId());
                sale.setUnitsSold(editedSale.getUnitsSold());
                persistance.updateData(null, null, null, null, salesList);
                return sale;
            }
        }
        return null;
    }
}
