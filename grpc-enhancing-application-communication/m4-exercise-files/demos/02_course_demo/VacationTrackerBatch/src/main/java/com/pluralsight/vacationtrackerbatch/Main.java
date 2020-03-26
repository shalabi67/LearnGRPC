package com.pluralsight.vacationtrackerbatch;

import com.pluralsight.vacationtrackerbatch.dataaccess.EmployeeDao;
import com.pluralsight.vacationtrackerbatch.dataaccess.EmployeeDaoImpl;
import com.pluralsight.vacationtrackerbatch.models.Employee;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static EmployeeDao employeeDao = new EmployeeDaoImpl();

    public static void main(String args[]) {
        logger.debug("Batch processing started");
        logger.debug("Retrieving all employee records");
        List<Employee> employees = employeeDao.getAll();

        logger.debug("Processing records");
        for (Employee emp : employees) {
            double newVacationAccrued = emp.getVacationAccrued() + emp.getVacationAccrualRate();
            logger.debug("Changing vacation accrued for badge '{}' from {} to {}.", emp.getBadgeNumber(), emp.getVacationAccrued(), newVacationAccrued);
            emp.setVacationAccrued(newVacationAccrued);
        }

        logger.debug("Saving employee records");
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            employeeDao.save(iterator.next());
        }

        logger.debug("Batch job complete");
    }
}
