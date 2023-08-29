package org.example.Repository;

import org.example.Model.*;
import org.example.Utils.Constant;
import org.example.Utils.Persistance;

import java.util.ArrayList;
import java.util.List;

public class PORepo {
    List<PO> poList;

    private final User currentUser;

    Persistance persistance = new Persistance();

    public PORepo(User user){
        poList = persistance.getPOList();
        this.currentUser = user;
    }

    private void checkAccess(String functionality) throws IllegalAccessException {
        // REFRESH DATA
        poList = persistance.getPOList();
        Constant.checkAccess(List.of("PoList"), List.of("PoEntry", "PoDelete", "PoEdit", "PoList"), functionality, currentUser);
    }

    public PR addPO(PO po){
        try {
            checkAccess("PrEntry");
        } catch (IllegalAccessException ignored) {
            return null;
        }

        poList.add(po);
        persistance.updateData(null, poList, null, null, null);
        return po;
    }

    public void deletePo(int poId){
        try {
            checkAccess("PoDelete");
        } catch (IllegalAccessException ignored) {
            return;
        }

        // Delete Supplier and his items altogether
        poList.removeIf(po -> poId == po.getPoId());
        persistance.updateData(null, poList, null, null, null);
    }

    public PO prEdit(PO editedPo){
        try {
            checkAccess("PrEdit");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        for(PO po: poList){
            if(po.getPoId() == editedPo.getPoId()){
                po.setApprovalStatus(editedPo.getApprovalStatus());
                po.setItemId(editedPo.getItemId());
                po.setQuantityRequired(editedPo.getQuantityRequired());
                po.setSuppId(editedPo.getSuppId());
                persistance.updateData(null, poList, null, null, null);
                return po;
            }
        }
        return null;
    }

    public List<PO> getPOList(){
        try {
            checkAccess("PoList");
        } catch (IllegalAccessException ignored) {
            return null;
        }
        return persistance.getPOList();
    }
}
