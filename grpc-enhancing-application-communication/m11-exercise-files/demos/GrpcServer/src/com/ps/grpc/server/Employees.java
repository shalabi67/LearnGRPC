package com.ps.grpc.server;

import com.ps.grpc.messages.Messages;
import java.util.ArrayList;

public class Employees extends ArrayList<Messages.Employee> {

    private static Employees employees;
    
    public static Employees getInstance() {
        if (employees == null) {
            employees = new Employees();
        }
        return employees;
    }
    
    private Employees() {
        this.add(Messages.Employee.newBuilder()
                .setId(1)
                .setBadgeNumber(2080)
                .setFirstName("Grace")
                .setLastName("Decker")
                .setVacationAccrualRate(2)
                .setVacationAccrued(30)
                .build());
        
        this.add(Messages.Employee.newBuilder()
                .setId(2)
                .setBadgeNumber(7538)
                .setFirstName("Amity")
                .setLastName("Fuller")
                .setVacationAccrualRate(2.3f)
                .setVacationAccrued(23.4f)
                .build());

        this.add(Messages.Employee.newBuilder()
                .setId(1)
                .setBadgeNumber(5144)
                .setFirstName("Keaton")
                .setLastName("Willis")
                .setVacationAccrualRate(3)
                .setVacationAccrued(31.7f)
                .build());
    }
    
}
