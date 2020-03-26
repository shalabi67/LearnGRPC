package vactracker

import "errors"
import "fmt"

func GetAllEmployees() ([]*Employee, error) {
	return employeeData, nil
}

func GetEmployeeByBadgeNumber(badgeNumber int) (*Employee, error) {
	for _, emp := range employeeData {
		if emp.BadgeNumber == badgeNumber {
			return emp, nil
		}
	}
	return nil, errors.New(fmt.Sprintf("No record found with badge number %v", badgeNumber))
}

func SaveEmployee(employee *Employee) (*Employee, error) {
	if employee.Id == 0 {
		employee.Id = getNextEmployeeId()
	} else {
		for i := range employeeData { 
			if employeeData[i].Id == employee.Id {
				employeeData = append(employeeData[:i], employeeData[i+1:]...)
				break
			}
		}
	}
	employeeData = append(employeeData, employee)
	return employee, nil
}
