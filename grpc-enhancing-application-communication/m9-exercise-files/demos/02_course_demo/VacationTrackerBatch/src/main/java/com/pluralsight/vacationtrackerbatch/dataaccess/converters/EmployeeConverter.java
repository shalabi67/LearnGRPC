/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pluralsight.vacationtrackerbatch.dataaccess.converters;

import com.pluralsight.vacationtrackerbatch.messages.Messages;
import com.pluralsight.vacationtrackerbatch.models.Employee;

/**
 *
 * @author Mike
 */
public class EmployeeConverter {
    public Employee toModel(Messages.Employee employee) {
        Employee result = new Employee();
        
        result.setId(employee.getId());
        result.setBadgeNumber(employee.getBadgeNumber());
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());
        result.setVacationAccuralRate(employee.getVacationAccrualRate());
        result.setVacationAccrued(employee.getVacationAccrued());
        
        return result;
    }
    
}
