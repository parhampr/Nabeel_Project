package org.example.Repository;

import org.example.Model.Item;
import org.example.Model.PR;
import org.example.Model.Supplier;
import org.example.Model.User;
import org.example.Utils.Constant;
import org.example.Utils.Persistance;

import java.util.ArrayList;
import java.util.List;

public class PRRepo {
    List<Item> itemList;
    List<Supplier> supplierList;
    List<PR> prList;

    private final User currentUser;

    Persistance persistance = new Persistance();

    public PRRepo(User user){
        itemList = persistance.getItemList();
        supplierList = persistance.getSupplierList();
        prList = persistance.getPRList();
        persistance.updateData(null, null, prList, null, null);
        this.currentUser = user;
    }

    private void checkAccess(String functionality) throws IllegalAccessException {
        // REFRESH DATA
        itemList = persistance.getItemList();
        supplierList = persistance.getSupplierList();
        prList = persistance.getPRList();
        Constant.checkAccess(List.of("PrEntry", "PrGet", "PrDelete", "PrEdit"), List.of("PrGet"), functionality, currentUser);
    }

    public PR addPR(PR pr){
        try {
            checkAccess("PrEntry");
        } catch (IllegalAccessException ignored) {
            return null;
        }

        prList.add(pr);
        persistance.updateData(null, null, prList, null, null);
        return pr;
    }

    public PR getPr(int prID){
        try {
            checkAccess("PrGet");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        for (PR pr: prList) {
            if(pr.getPrID() == prID)
                return pr;
        }
        return null;
    }

    public void deletePr(int prId){
        try {
            checkAccess("PrDelete");
        } catch (IllegalAccessException ignored) {
            return;
        }

        // Delete Supplier and his items altogether
        prList.removeIf(pr -> prId == pr.getPrID());
        persistance.updateData(null, null, prList, null, null);
    }

    public PR prEdit(PR editedPr){
        try {
            checkAccess("PrEdit");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        for(PR pr: prList){
            if(pr.getPrID() == editedPr.getPrID()){
                pr.setItemId(editedPr.getItemId());
                pr.setQuantityRequired(editedPr.getQuantityRequired());
                pr.setSuppId(editedPr.getSuppId());
                persistance.updateData(null, null, prList, null, null);
                return pr;
            }
        }
        return null;
    }
}
