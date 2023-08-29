package org.example.Repository;

import org.example.Model.Item;
import org.example.Model.Supplier;
import org.example.Model.User;
import org.example.Utils.Constant;
import org.example.Utils.Persistance;

import java.util.ArrayList;
import java.util.List;

public class SuppRepo {
    List<Item> itemList = new ArrayList<>();
    List<Supplier> supplierList;

    private final User currentUser;

    Persistance persistance = new Persistance();

    public SuppRepo(User user){
        supplierList = persistance.getSupplierList();
        this.currentUser = user;
    }

    private void checkAccess(String functionality) throws IllegalAccessException {
        // REFRESH DATA
        itemList = persistance.getItemList();
        supplierList = persistance.getSupplierList();
        Constant.checkAccess(List.of("SuppEntry", "SuppDelete", "SuppEdit"), List.of("SupplierList"), functionality, currentUser);
    }

    public Supplier addSupp(Supplier supplier){
        try {
            checkAccess("SuppEntry");
        } catch (IllegalAccessException ignored) {
            return null;
        }

        supplierList.add(supplier);
        persistance.updateData(null, null, null, supplierList, null);
        return supplier;
    }

    public void deleteSupplier(int suppId){
        try {
            checkAccess("SuppDelete");
        } catch (IllegalAccessException ignored) {
            return;
        }

        // Delete Supplier and his items altogether
        supplierList.removeIf(supplier -> suppId == supplier.getSuppId());
        itemList.removeIf(item -> suppId == item.getSuppId());
        persistance.updateData(itemList, null, null, supplierList, null);
    }

    public Supplier suppEdit(Supplier editedSupp){
        try {
            checkAccess("SuppEdit");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        for(Supplier supplier: supplierList){
            if(supplier.getSuppId() == editedSupp.getSuppId()){
                supplier.setSuppName(editedSupp.getSuppName());
                persistance.updateData(null, null, null, supplierList, null);
                return supplier;
            }
        }
        return null;
    }

    public List<Supplier> getSuppliers(){
        try {
            checkAccess("SupplierList");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        return persistance.getSupplierList();
    }
}
