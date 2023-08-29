package org.example.Utils;
import org.example.Model.User;
import java.util.List;

public class Constant {
    public enum UserRole {
        SALES_MANAGER, PURCHASE_MANAGER, ADMINISTRATOR
    }

    public static void checkAccess(List<String> ONLY_SALES_METHODS, List<String> ONLY_PURCHASE_MANAGER, String functionality, User currentUser) throws IllegalAccessException {
        switch (currentUser.getRole()) {
            case SALES_MANAGER -> {
                if (!ONLY_SALES_METHODS.contains(functionality)) {
                    System.out.println("Access Denied: You are not allowed to do this request");
                    throw new IllegalAccessException("Access Denied");
                }
            }
            case PURCHASE_MANAGER -> {
                if (!ONLY_PURCHASE_MANAGER.contains(functionality)) {
                    System.out.println("Access Denied: You are not allowed to do this request");
                    throw new IllegalAccessException("Access Denied");
                }
            }
            case ADMINISTRATOR -> {
            }
        }
    }

}
