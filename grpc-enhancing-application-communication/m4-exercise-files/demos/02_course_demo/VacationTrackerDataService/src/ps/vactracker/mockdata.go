package vactracker

import (
	"time"
	"log"
)

var (
	employeeData []*Employee
	nextEmployeeId int = 0
	nextVacationId int = 0
)

func init() {
	employeeData = []*Employee{
		&Employee{
			Id: getNextEmployeeId(),
			BadgeNumber: 64927,
			FirstName: "Ann",
			LastName: "Jenkins",
			VacationAccrualRate: 6.14,
			VacationAccrued: 80,
			Vacations: []*Vacation{},
		},
		&Employee {
			Id: getNextEmployeeId(),
			BadgeNumber: 72453,
			FirstName: "Chris",
			LastName: "Baker",
			VacationAccrualRate:6.14,
			VacationAccrued: 16,
			Vacations: []*Vacation{
				&Vacation{
					Id: getNextVacationId(),
					StartDate: getLocalDate(2016, 3, 7),
					Duration: 16,
				},
				&Vacation{
					Id: getNextVacationId(),
					StartDate: getLocalDate(2016, 5, 30),
					Duration: 40,
				},
				&Vacation{
					Id: getNextVacationId(),
					StartDate: getLocalDate(2016, 6, 6),
				},
			},
		},
		&Employee {
			Id: getNextEmployeeId(),
			BadgeNumber: 75257,
			FirstName: "Thomas",
			LastName: "Welch",
			VacationAccrualRate: 6.14,
			VacationAccrued: 64,
			Vacations: []*Vacation{
				&Vacation {
					Id: getNextVacationId(),
					StartDate: getLocalDate(2016, 4, 29),
					Duration: 8,
				},
				&Vacation{
					Id: getNextVacationId(),
					StartDate: getLocalDate(2016, 5, 13),
					Duration: 8,
				},
			},
		},
		&Employee{
			Id: getNextEmployeeId(),
			BadgeNumber: 80003,
			FirstName: "Frank",
			LastName: "Hunter",
			VacationAccrualRate: 6.14,
			VacationAccrued: 48,
			Vacations: []*Vacation{
				&Vacation {
					Id: getNextVacationId(),
					StartDate: getLocalDate(2016, 5, 6), Duration : 4 },
				&Vacation {
					Id: getNextVacationId(), 
					StartDate: getLocalDate(2016, 5, 13), 
					Duration: 4,
				},
				&Vacation {
					Id: getNextVacationId(), 
					StartDate: getLocalDate(2016, 5, 27), 
					Duration: 4,
				},
				&Vacation {
					Id: getNextVacationId(), 
					StartDate: getLocalDate(2016, 5, 20), 
					Duration: 4,
				},
				&Vacation {
					Id: getNextVacationId(), 
					StartDate: getLocalDate(2016, 6, 16), 
					Duration: 16,
				},
			},
		},
	}
}

func getNextVacationId() int {
	nextVacationId++
	return nextVacationId
}

func getNextEmployeeId() int {
	nextEmployeeId++
	return nextEmployeeId
}

func getLocalDate(year int, month time.Month, day int) *time.Time {
	loc, err := time.LoadLocation("America/New_York")
	if err != nil {
		log.Fatal(err)
	}
	result := time.Date(year, month, day, 0, 0, 0, 0, loc)
	return &result
}