package Asm2;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String customerId;

    public User(String name, String customerId) {

        if (customerId.length()== 12 && customerId.matches("[0-9]+")){
            this.customerId = customerId;
        }
        else {
            System.out.println("Error , invalid customer ID value");
            return;
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        if (customerId.length()== 12 && customerId.matches("[0-9]+")){
            this.customerId = customerId;
        }
        else System.out.println("Error , invalid customer ID value");
    }
}
