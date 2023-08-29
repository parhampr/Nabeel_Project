package org.example;

import org.example.Model.*;
import org.example.Repository.*;
import org.example.Utils.Constant;
import org.example.Utils.Persistance;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Persistance p = new Persistance();
        p.setUpPersistance();

        // Create users
        User salesManager = new User("salesManager", "password123", Constant.UserRole.SALES_MANAGER);
        User purchaseManager = new User("purchaseManager", "password123", Constant.UserRole.PURCHASE_MANAGER);
        User admin = new User("admin", "password123", Constant.UserRole.ADMINISTRATOR);

        // Create repositories
        ItemRepo itemRepo = new ItemRepo(admin);
        PORepo poRepo = new PORepo(admin);
        PRRepo prRepo = new PRRepo(admin);
        SalesRepo salesRepo = new SalesRepo(purchaseManager);
        SuppRepo suppRepo = new SuppRepo(salesManager);

        // Add a supplier
        Supplier supplier = new Supplier("SupplierA");
        suppRepo.addSupp(supplier);
        printSupplier(supplier);

        // Add an item
        Item item = new Item(supplier.getSuppId(), "ItemA", 100);
        itemRepo.addItem(item);
        printItem(item);

        // Add a purchase request
        PR pr = new PR(supplier.getSuppId(), item.getItemID(), 50);
        prRepo.addPR(pr);
        printPR(pr);

        // Add a purchase order
        PO po = new PO(supplier.getSuppId(), item.getItemID(), 50);
        poRepo.addPO(po);
        printPO(po);

        // Add a sale
        ItemSales sale = new ItemSales(item.getItemID(), 10);
        salesRepo.addSale(sale);
        printSale(sale);

        // Demonstrate role-based access control
        try {
            // Sales manager trying to add a supplier (should fail)
            suppRepo.addSupp(new Supplier("SupplierB"));
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            // Purchase manager trying to add a sale (should fail)
            salesRepo.addSale(new ItemSales(item.getItemID(), 20));
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Print all items
        List<Item> items = itemRepo.getItems();
        System.out.println("\nAll Items:");
        for (Item i : items) {
            printItem(i);
        }
    }

    public static void printSupplier(Supplier supplier) {
        System.out.println("Supplier ID: " + supplier.getSuppId() + ", Name: " + supplier.getSuppName());
    }

    public static void printItem(Item item) {
        System.out.println("Item ID: " + item.getItemID() + ", Name: " + item.getItemName() + ", Quantity Available: " + item.getQuantityAvailable());
    }

    public static void printPR(PR pr) {
        System.out.println("PR ID: " + pr.getPrID() + ", Item ID: " + pr.getItemId() + ", Quantity Required: " + pr.getQuantityRequired());
    }

    public static void printPO(PO po) {
        System.out.println("PO ID: " + po.getPoId() + ", Item ID: " + po.getItemId() + ", Quantity Required: " + po.getQuantityRequired() + ", Approval Status: " + po.getApprovalStatus());
    }

    public static void printSale(ItemSales sale) {
        System.out.println("Sale ID: " + sale.getSaleId() + ", Item ID: " + sale.getItemId() + ", Units Sold: " + sale.getUnitsSold() + ", Date: " + sale.getDate());
    }
}