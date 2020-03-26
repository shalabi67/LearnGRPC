package main

import (
	"log"
	"net"
	"fmt"

	"google.golang.org/grpc"
	"ps/vactracker/pb"
)

const address = "localhost:8080"

func main() {
	lis, err := net.Listen("tcp", address)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	server := grpc.NewServer()
	pb.RegisterEmployeeServiceServer(server, &employeeServer{})
	go server.Serve(lis)
	fmt.Printf("Server started and listening on %v\n", address)
	var s string
	fmt.Scanln(&s)
}
