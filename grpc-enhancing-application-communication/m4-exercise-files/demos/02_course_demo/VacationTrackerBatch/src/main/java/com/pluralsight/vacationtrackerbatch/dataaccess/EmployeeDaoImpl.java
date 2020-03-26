package com.pluralsight.vacationtrackerbatch.dataaccess;

import com.pluralsight.vacationtrackerbatch.models.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeDaoImpl implements EmployeeDao {

    private List<Employee> employees;
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    public EmployeeDaoImpl() {
        employees = new ArrayList<>();
        Employee emp = new Employee();
        emp.setId(1);
        emp.setBadgeNumber(64927);
        emp.setFirstName("Ann");
        emp.setLastName("Jenkins");
        emp.setVacationAccrualRate(6.14);
        emp.setVacationAccrued(80);
        employees.add(emp);
        
        emp = new Employee();
        emp.setId(2);
        emp.setBadgeNumber(72453);
        emp.setFirstName("Chris");
        emp.setLastName("Baker");
        emp.setVacationAccrualRate(6.14);
        emp.setVacationAccrued(16);
        employees.add(emp);
        
        emp = new Employee();
        emp.setId(3);
        emp.setBadgeNumber(75257);
        emp.setFirstName("Thomas");
        emp.setLastName("Welch");
        emp.setVacationAccrualRate(6.14);
        emp.setVacationAccrued(64);
        employees.add(emp);
        
        emp = new Employee();
        emp.setId(4);
        emp.setBadgeNumber(8003);
        emp.setFirstName("Frank");
        emp.setLastName("Hunter");
        emp.setVacationAccrualRate(6.14);
        emp.setVacationAccrued(48);
        employees.add(emp);
        
    }
    
    @Override
    public List<Employee> getAll() {
        logger.debug("Retrieving all employees");
        
        return new ArrayList<Employee>(employees);
    }

    @Override
    public Employee save(Employee employee) {
        logger.debug("Attempting to save employee with badge number: '{}'", employee.getBadgeNumber());
        
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employeeNow = iterator.next();
            if (employeeNow.getBadgeNumber() == employee.getBadgeNumber()) {
                iterator.remove();
                break;
            }
        }
        
        employees.add(employee);
        
        logger.debug("Successfully saved employee with badge number '{}'", employee.getBadgeNumber());
        
        return employee;
        
    }
    
}
