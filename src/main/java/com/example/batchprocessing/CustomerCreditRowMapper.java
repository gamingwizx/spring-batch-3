package com.example.batchprocessing;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerCreditRowMapper implements RowMapper<InputPerson> {
    public static final String NAME_COLUMN = "name";
    public static final String JOB_TITLE_COLUMN = "job_titles";
    public static final String DEPARTMENT_COLUMN = "department";
    public static final String FULL_OR_PART_TIME_COLUMN = "full_or_part_time";
    public static final String SALARY_OR_HOURLY_COLUMN = "salary_or_hourly";
    public static final String TYPICAL_HOURS_COLUMN = "typical_hours";
    public static final String ANNUAL_SALARY_COLUMN = "annual_salary";
    public static final String HOURLY_RATE_COLUMN = "hourly_rate";

    public InputPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
        InputPerson inputPerson = new InputPerson();

        inputPerson.setName(rs.getString(NAME_COLUMN));
        inputPerson.setJobTitles(rs.getString(JOB_TITLE_COLUMN));
        inputPerson.setDepartment(rs.getString(DEPARTMENT_COLUMN));
        inputPerson.setFullOrPartTime(rs.getString(FULL_OR_PART_TIME_COLUMN));
        inputPerson.setSalaryOrHourly(rs.getString(SALARY_OR_HOURLY_COLUMN));
        inputPerson.setTypicalHours(rs.getInt(TYPICAL_HOURS_COLUMN));
        inputPerson.setAnnualSalary(rs.getDouble(ANNUAL_SALARY_COLUMN));
        inputPerson.setHourlyRate(rs.getDouble(HOURLY_RATE_COLUMN));

        return inputPerson;
    }

}
