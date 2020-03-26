package com.pluralsight.vacationtrackerbatch.models;

public class Employee {
    private int id;
    private int badgeNumber;
    private String firstName;
    private String lastName;
    private double vacationAccrualRate;
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

    public double getVacationAccrualRate() {
        return vacationAccrualRate;
    }

    public void setVacationAccrualRate(double vacationAccrualRate) {
        this.vacationAccrualRate = vacationAccrualRate;
    }

    public double getVacationAccrued() {
        return vacationAccrued;
    }

    public void setVacationAccrued(double vacationAccrued) {
        this.vacationAccrued = vacationAccrued;
    }
}
