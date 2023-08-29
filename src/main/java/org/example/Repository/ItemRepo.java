package org.example.Repository;

import org.example.Model.Item;
import org.example.Model.Supplier;
import org.example.Model.User;
import org.example.Utils.Constant;
import org.example.Utils.Persistance;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
    List<Item> itemList;
    List<Supplier> supplierList;

    private final User currentUser;

    Persistance persistance = new Persistance();

    public ItemRepo(User user){
        itemList = persistance.getItemList();
        supplierList = persistance.getSupplierList();
        this.currentUser = user;
    }

    private void checkAccess(String functionality) throws IllegalAccessException {
        // REFRESH DATA
        itemList = persistance.getItemList();
        supplierList = persistance.getSupplierList();
        Constant.checkAccess(List.of("ItemEntry", "ItemDelete", "ItemEdit"), List.of("ItemList"), functionality, currentUser);
    }

    public Item addItem(Item item){
        try {
            checkAccess("ItemEntry");
        } catch (IllegalAccessException ignored) {
            return null;
        }

        System.out.println(supplierList);
        for (Supplier supp: supplierList)
            if(supp.getSuppId() == item.getSuppId()){
                itemList.add(item);
                persistance.updateData(itemList, null, null, null, null);
                return item;
            }
        System.out.println("Cannot add Item as Supplier is invalid");
        return null;
    }

    public void deleteItem(int itemId){
        try {
            checkAccess("ItemDelete");
        } catch (IllegalAccessException ignored) {
            return;
        }

        itemList.removeIf(item -> itemId == item.getItemID());
        persistance.updateData(itemList, null, null, null, null);
    }

    public Item editItem(Item editedItem){
        try {
            checkAccess("ItemEdit");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        for(Item item: itemList){
            if(item.getItemID() == editedItem.getItemID()){
                item.setItemName(editedItem.getItemName());
                item.setSuppId(editedItem.getSuppId());
                item.setQuantityAvailable(editedItem.getQuantityAvailable());
                persistance.updateData(itemList, null, null, null, null);
                return item;
            }
        }
        return null;
    }

    public List<Item> getItems(){
        try {
            checkAccess("ItemList");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        return persistance.getItemList();
    }
}
