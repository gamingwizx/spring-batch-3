package com.example.batchprocessing;

public class OutputPerson {
    private String firstName;
    private String lastName;
    private int typicalHours;
    private double annualSalary;
    private double hourlyRate;
    private double monthlySalary;

    // Constructor
    public OutputPerson(String firstName, String lastName, int typicalHours, double annualSalary, double hourlyRate, double monthlySalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.typicalHours = typicalHours;
        this.annualSalary = annualSalary;
        this.hourlyRate = hourlyRate;
        this.monthlySalary = monthlySalary;
    }

    // Default constructor (needed for frameworks like Spring)
    public OutputPerson() {
    }

    // Getters and Setters
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

    public int getTypicalHours() {
        return typicalHours;
    }

    public void setTypicalHours(int typicalHours) {
        this.typicalHours = typicalHours;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    // toString() method for debugging/logging
    @Override
    public String toString() {
        return "OutputPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", typicalHours=" + typicalHours +
                ", annualSalary=" + annualSalary +
                ", hourlyRate=" + hourlyRate +
                ", monthlySalary=" + monthlySalary +
                '}';
    }
}

