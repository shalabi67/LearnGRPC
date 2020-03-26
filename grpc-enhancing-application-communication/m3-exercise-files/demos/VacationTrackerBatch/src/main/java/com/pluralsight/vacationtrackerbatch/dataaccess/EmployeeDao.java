package com.pluralsight.vacationtrackerbatch.dataaccess;

import com.pluralsight.vacationtrackerbatch.models.Employee;
import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();
    Employee save(Employee employee);
}
