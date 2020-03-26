package main

import (
	"ps/vactracker"
	"ps/vactracker/pb"
	"time"
)

func convertEmployeeToMessage(emp *vactracker.Employee) *pb.Employee {
	return &pb.Employee{
		Id:                  int32(emp.Id),
		BadgeNumber:         int32(emp.BadgeNumber),
		FirstName:           emp.FirstName,
		LastName:            emp.LastName,
		VacationAccrualRate: emp.VacationAccrualRate,
		VacationAccrued:     emp.VacationAccrued,
		Vacations:           convertVacationsToMessages(emp.Vacations),
	}
}

func convertVacationsToMessages(vacations []*vactracker.Vacation) []*pb.Vacation {
	result := []*pb.Vacation{}
	if vacations == nil {
		return result
	}
	for _, vac := range vacations {
		result = append(result, convertVacationToMessage(vac))
	}
	return result
}

func convertVacationToMessage(vac *vactracker.Vacation) *pb.Vacation {
	return &pb.Vacation{
		Id:          int32(vac.Id),
		StartDate:   vac.StartDate.Unix(),
		Duration:    vac.Duration,
		IsCancelled: vac.IsCancelled,
	}
}

func convertMessageToEmployee(msg *pb.Employee) *vactracker.Employee {
	return &vactracker.Employee{
		Id:                  int(msg.Id),
		BadgeNumber:         int(msg.BadgeNumber),
		FirstName:           msg.FirstName,
		LastName:            msg.LastName,
		VacationAccrualRate: msg.VacationAccrualRate,
		VacationAccrued:     msg.VacationAccrued,
		Vacations:           convertMessagesToVacations(msg.Vacations),
	}
}
func convertMessagesToVacations(msg []*pb.Vacation) []*vactracker.Vacation {
	result := []*vactracker.Vacation{}
	if msg == nil {
		return result
	}
	for _, vac := range msg {
		result = append(result, convertMessageToVacation(vac))
	}
	return result
}

func convertMessageToVacation(msg *pb.Vacation) *vactracker.Vacation {
	startDate := time.Unix(msg.StartDate, 0)
	return &vactracker.Vacation{
		Id:          int(msg.Id),
		StartDate:   &startDate,
		Duration:    msg.Duration,
		IsCancelled: msg.IsCancelled,
	}
}
