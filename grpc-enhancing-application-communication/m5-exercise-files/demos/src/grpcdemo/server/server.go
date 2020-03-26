package main

import (
	"errors"
	"fmt"
	"grpcdemo/pb"
	"io"
	"log"
	"net"

	"golang.org/x/net/context"
	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials"
	"google.golang.org/grpc/metadata"
)

const port = ":9000"

func main() {
	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatal(err)
	}
	creds, err := credentials.NewServerTLSFromFile("cert.pem", "key.pem")
	if err != nil {
		log.Fatal(err)
	}
	opts := []grpc.ServerOption{grpc.Creds(creds)}
	s := grpc.NewServer(opts...)
	pb.RegisterEmployeeServiceServer(s, new(employeeServer))
	log.Println("Starting server on port " + port)
	s.Serve(lis)
}

type employeeServer struct{}

func (s *employeeServer) GetByBadgeNumber(ctx context.Context, req *pb.GetByBadgeNumberRequest) (*pb.EmployeeResponse, error) {
	if md, ok := metadata.FromContext(ctx); ok {
		fmt.Printf("Metadata received: %v\n", md)
	}
	for _, e := range employees {
		if req.BadgeNumber == e.BadgeNumber {
			return &pb.EmployeeResponse{Employee: &e}, nil
		}
	}
	return nil, errors.New("Employee not found")
}

func (s *employeeServer) GetAll(req *pb.GetAllRequest, stream pb.EmployeeService_GetAllServer) error {
	for _, e := range employees {
		stream.Send(&pb.EmployeeResponse{Employee: &e})
	}
	return nil
}
func (s *employeeServer) Save(ctx context.Context, req *pb.EmployeeRequest) (*pb.EmployeeResponse, error) {
	employees = append(employees, *req.Employee)
	return &pb.EmployeeResponse{Employee: req.Employee}, nil
}
func (s *employeeServer) SaveAll(stream pb.EmployeeService_SaveAllServer) error {
	for {
		emp, err := stream.Recv()
		if err == io.EOF {
			break
		}
		if err != nil {
			return err
		}
		employees = append(employees, *emp.Employee)
		stream.Send(&pb.EmployeeResponse{Employee: emp.Employee})
	}
	for _, e := range employees {
		fmt.Println(e)
	}
	return nil
}
func (s *employeeServer) AddPhoto(stream pb.EmployeeService_AddPhotoServer) error {
	imgData := []byte{}
	for {
		data, err := stream.Recv()
		if err == io.EOF {
			fmt.Printf("File received with length: %v\n", len(imgData))
			return stream.SendAndClose(&pb.AddPhotoResponse{IsOk: true})
		}
		if err != nil {
			return err
		}
		imgData = append(imgData, data.Data...)
	}
}
