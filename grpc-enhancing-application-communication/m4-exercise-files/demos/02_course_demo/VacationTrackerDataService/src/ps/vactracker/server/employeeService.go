package main

import (
	"io"
	"ps/vactracker"
	"ps/vactracker/pb"

	"golang.org/x/net/context"
)

type employeeServer struct {
}

func (es *employeeServer) GetByBadgeNumber(ctx context.Context,
	req *pb.GetByBadgeNumberRequest) (*pb.EmployeeResponse, error) {
	employee, err := vactracker.GetEmployeeByBadgeNumber(int(req.BadgeNumber))
	if err != nil {
		return nil, err
	}
	response := &pb.EmployeeResponse{Employee: convertEmployeeToMessage(employee)}
	return response, nil
}

func (es *employeeServer) GetAll(req *pb.GetAllRequest,
	stream pb.EmployeeService_GetAllServer) error {
	employees, err := vactracker.GetAllEmployees()
	if err != nil {
		return err
	}
	for _, emp := range employees {
		if err = stream.Send(&pb.EmployeeResponse{Employee: convertEmployeeToMessage(emp)}); err != nil {
			return err
		}
	}
	return nil
}

func (es *employeeServer) Save(ctx context.Context,
	req *pb.EmployeeRequest) (*pb.EmployeeResponse, error) {
	emp := convertMessageToEmployee(req.Employee)
	emp, err := vactracker.SaveEmployee(emp)
	if err != nil {
		return nil, err
	}
	response := &pb.EmployeeResponse{Employee: convertEmployeeToMessage(emp)}
	return response, nil
}

func (es *employeeServer) SaveAll(stream pb.EmployeeService_SaveAllServer) error {
	for {
		empMsg, err := stream.Recv()
		if err == io.EOF {
			return nil
		}
		if err != nil {
			return err
		}
		emp := convertMessageToEmployee(empMsg.Employee)
		emp, err = vactracker.SaveEmployee(emp)
		if err != nil {
			return err
		}
		response := convertEmployeeToMessage(emp)
		err = stream.Send(&pb.EmployeeResponse{Employee: response})

		if err != nil {
			return err
		}
	}
}
