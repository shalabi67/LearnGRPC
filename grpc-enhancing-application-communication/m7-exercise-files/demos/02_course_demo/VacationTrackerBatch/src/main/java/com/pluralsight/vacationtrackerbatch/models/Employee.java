package com.pluralsight.vacationtrackerbatch.models;

public class Employee {
    private int id;
    private int badgeNumber;
    private String firstName;
    private String lastName;
    private double vacationAccuralRate;
    private double vacationAccrued;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(int badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getVacationAccuralRate() {
        return vacationAccuralRate;
    }

    public void setVacationAccuralRate(double vacationAccuralRate) {
        this.vacationAccuralRate = vacationAccuralRate;
    }

    public double getVacationAccrued() {
        return vacationAccrued;
    }

    public void setVacationAccrued(double vacationAccrued) {
        this.vacationAccrued = vacationAccrued;
    }
}
