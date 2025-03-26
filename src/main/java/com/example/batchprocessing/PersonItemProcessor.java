package com.example.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PersonItemProcessor implements ItemProcessor<InputPerson, OutputPerson> {
    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public OutputPerson process(final InputPerson person) {
        System.out.println("Processing records...");
        String fullName = person.getName().trim();
        String[] parts = fullName.split(" ");
        String firstName = parts[0];
        String lastName = "";

        if (parts.length > 1) {
            firstName = parts[0];
            lastName = parts[1].trim();
        }


        int typicalHours = person.getTypicalHours() != null ? person.getTypicalHours() : 0;
        double annualSalary = person.getAnnualSalary() != null ? person.getAnnualSalary() : 0.0;
        double hourlyRate = person.getHourlyRate() != null ? person.getHourlyRate() : 0.0;
        double monthlySalary = 0;
        if (annualSalary == 0) {
            annualSalary = typicalHours * hourlyRate * 52;
        } else {
            typicalHours = 8;
            monthlySalary = Math.ceil(annualSalary / 12 * 100) / 100.0;
            hourlyRate = Math.ceil(annualSalary / 52 / typicalHours * 100) / 100.0;
        }

        final OutputPerson updatedPerson = new OutputPerson(firstName, lastName, typicalHours, annualSalary, hourlyRate, monthlySalary);

        log.info("Converting ({}) into ({})", person, updatedPerson);

        return updatedPerson;
    }
}