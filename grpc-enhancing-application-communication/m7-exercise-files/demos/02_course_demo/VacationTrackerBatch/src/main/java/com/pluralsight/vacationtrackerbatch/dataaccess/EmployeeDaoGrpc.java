/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight.vacationtrackerbatch.dataaccess;

import com.google.protobuf.GeneratedMessageV3;
import com.pluralsight.vacationtrackerbatch.dataaccess.converters.EmployeeConverter;
import com.pluralsight.vacationtrackerbatch.messages.EmployeeServiceGrpc;
import com.pluralsight.vacationtrackerbatch.messages.Messages;
import com.pluralsight.vacationtrackerbatch.models.Employee;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mike
 */
public class EmployeeDaoGrpc implements EmployeeDao {
    
    private static final String host = "localhost";
    private static final int port = 8080;

    EmployeeServiceGrpc.EmployeeServiceBlockingStub client;
    
    EmployeeConverter employeeConverter = new EmployeeConverter();
    
    public EmployeeDaoGrpc() {
        Channel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        client = EmployeeServiceGrpc.newBlockingStub(channel);
    }
    

    @Override
    public List<Employee> getAll() {
        List<Employee> results = new ArrayList<>();
        
        Iterator<Messages.EmployeeResponse> responseIterator = client.getAll(Messages.GetAllRequest.getDefaultInstance());
        while (responseIterator.hasNext()) {
            Messages.EmployeeResponse response = responseIterator.next();
            results.add(employeeConverter.toModel(response.getEmployee()));
            
        }
        
        return results;
    }

    @Override
    public Employee save(Employee employee) {
        Messages.GetByBadgeNumberRequest getByBadgeNumberRequest = Messages.GetByBadgeNumberRequest.newBuilder()
                .setBadgeNumber(employee.getBadgeNumber())
                .build();
        Messages.Employee response = client.getByBadgeNumber(getByBadgeNumberRequest).getEmployee();
        
        Messages.Employee updatedEmployee = Messages.Employee.newBuilder()
                .setId(employee.getId())
                .setBadgeNumber(employee.getBadgeNumber())
                .setFirstName(employee.getFirstName())
                .setLastName(employee.getLastName())
                .setVacationAccrualRate((float)employee.getVacationAccuralRate())
                .setVacationAccrued((float)employee.getVacationAccrued())
                .addAllVacations(response.getVacationsList())
                .build();
               
        Messages.EmployeeRequest employeeRequest = Messages.EmployeeRequest.newBuilder()
                .setEmployee(updatedEmployee)
                .build();
        
        response = client.save(employeeRequest).getEmployee();
        
        return employeeConverter.toModel(response);
        
        
    }
    
}
