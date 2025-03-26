package com.example.batchprocessing;

public class InputPerson {
    private String name;
    private String jobTitles;
    private String department;
    private String fullOrPartTime;
    private String salaryOrHourly;
    private Double annualSalary;
    private Integer typicalHours;
    private Double hourlyRate;

    // Default Constructor
    public InputPerson() {}

    // Parameterized Constructor
    public InputPerson(String name, String jobTitles, String department, String fullOrPartTime,
                       String salaryOrHourly, Double annualSalary, Integer typicalHours, Double hourlyRate) {
        this.name = name;
        this.jobTitles = jobTitles;
        this.department = department;
        this.fullOrPartTime = fullOrPartTime;
        this.salaryOrHourly = salaryOrHourly;
        this.annualSalary = annualSalary;
        this.typicalHours = typicalHours;
        this.hourlyRate = hourlyRate;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getJobTitles() { return jobTitles; }
    public void setJobTitles(String jobTitles) { this.jobTitles = jobTitles; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getFullOrPartTime() { return fullOrPartTime; }
    public void setFullOrPartTime(String fullOrPartTime) { this.fullOrPartTime = fullOrPartTime; }

    public String getSalaryOrHourly() { return salaryOrHourly; }
    public void setSalaryOrHourly(String salaryOrHourly) { this.salaryOrHourly = salaryOrHourly; }

    public Double getAnnualSalary() { return annualSalary; }
    public void setAnnualSalary(Double annualSalary) { this.annualSalary = annualSalary; }

    public Integer getTypicalHours() { return typicalHours; }
    public void setTypicalHours(Integer typicalHours) { this.typicalHours = typicalHours; }

    public Double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(Double hourlyRate) { this.hourlyRate = hourlyRate; }

    // Override toString for easy debugging
    @Override
    public String toString() {
        return "InputPerson{" +
                "name='" + name + '\'' +
                ", jobTitles='" + jobTitles + '\'' +
                ", department='" + department + '\'' +
                ", fullOrPartTime='" + fullOrPartTime + '\'' +
                ", salaryOrHourly='" + salaryOrHourly + '\'' +
                ", annualSalary=" + annualSalary +
                ", typicalHours=" + typicalHours +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}
