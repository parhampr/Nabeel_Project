package org.example.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistance {

    public void setUpPersistance() {
        this.updateData(new ArrayList<>(), new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    }
    private void updatePersistence(Object object, String name){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert list of objects to JSON string and save to a file
            objectMapper.writeValue(new File(name+".json"), object);

        } catch (IOException ignored) {
        }
    }

    public void updateData(List<Item> itemList, List<PO> poList, List<PR> prList, List<Supplier> supplierList, List<ItemSales> salesList){
        if(itemList != null)
            updatePersistence(itemList, "data/item");
        if(poList != null)
            updatePersistence(poList, "data/po");
        if(prList != null)
            updatePersistence(prList, "data/pr");
        if(supplierList != null)
            updatePersistence(supplierList, "data/supplier");
        if(salesList != null)
            updatePersistence(salesList, "data/sale");
    }

    public List<Item> getItemList(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("data/item.json"), new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
        return new ArrayList<>();
    }

    public List<PO> getPOList(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("data/po.json"), new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
        return new ArrayList<>();
    }

    public List<PR> getPRList(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("data/pr.json"), new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
        return new ArrayList<>();
    }

    public List<Supplier> getSupplierList(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("data/supplier.json"), new TypeReference<>() {
            });
        } catch (IOException ignored) {
        }
        return new ArrayList<>();
    }

    public List<ItemSales> getSalesList(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File("data/sale.json"), new TypeReference<>() {});
        } catch (IOException ignored) {
        }
        return new ArrayList<>();
    }
}

