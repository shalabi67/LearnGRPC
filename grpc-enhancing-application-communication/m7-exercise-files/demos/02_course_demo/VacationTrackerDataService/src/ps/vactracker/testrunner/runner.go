package main

import (
	"encoding/json"
	"flag"
	"fmt"
	"log"
	"os"
	"ps/grpcapp"
	"strings"
)

var (
	cmd = flag.String("cmd", "getAll", "Specify the command to exercise\n"+
		"\tSupported commands are:\n"+
		"\t\t- GetAll - retrieve all records\n"+
		"\t\t- SaveAll - save all of the records specified in the JSON file at 'path' and retrieve all records\n"+
		"\t\t- GetOne - retrieve the record with the specified 'badgeNumber'")
	badgeNumber = flag.Int("badge", 0, "Badge number to use if operation requires one")
	path        = flag.String("path", "data.json", "Relative path to JSON file containing records to save for 'saveAll' cmd")
)

func main() {
	flag.Parse()
	switch strings.ToLower(*cmd) {
	case "getall":
		getAll()
	case "saveall":
		saveAll()
	case "getone":
		getOne()
	default:
		log.Fatal("Unknown command")
	}
}

func getAll() {
	employees, err := grpcapp.GetAll()
	if err != nil {
		log.Fatal(err)
	}
	for _, emp := range employees {
		printEmployee(emp)
	}
}

func printEmployee(employee *grpcapp.Employee) {
	fmt.Printf("Id: %v, %v, %v [%v]\n", employee.Id, employee.LastName, employee.FirstName, employee.BadgeNumber)
	fmt.Print("Vacations\n---------\n")
	for _, vac := range employee.Vacations {
		fmt.Printf("Id: %v, Start Date: %v, Duration (h): %v, Active: %v\n", vac.Id, vac.StartDate.Format("2-Jan-2006"), vac.Duration, !vac.IsCancelled)
	}
	fmt.Println()
}

func saveAll() {
	file, err := os.Open(*path)
	defer file.Close()
	if err != nil {
		log.Fatalf("Unable to open file at '%v': %v\n", *path, err)
	}

	d := json.NewDecoder(file)
	employees := []*grpcapp.Employee{}

	err = d.Decode(&employees)

	if err != nil {
		log.Fatalf("Unable to retrieve Employee records from %v: %v", *path, err)
	}

	_, err = grpcapp.SaveAll(employees)

	if err != nil {
		log.Fatalf("Failed to save employees %v: %v", employees, err)
	}

	getAll()

}

func getOne() {
	emp, err := grpcapp.GetByBadgeNumber(*badgeNumber)

	if err != nil {
		log.Fatalf("Failed to retrieve employee with badge number %v: %v", *badgeNumber, err)
	}
	printEmployee(emp)
}
