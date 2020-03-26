package vactracker

import "time"

type Employee struct {
	Id int
	BadgeNumber int
	FirstName string
	LastName string
	VacationAccrualRate float32
	VacationAccrued float32
	Vacations []*Vacation
}

type Vacation struct{
	Id int
	StartDate *time.Time
	Duration float32
	IsCancelled bool 
}
