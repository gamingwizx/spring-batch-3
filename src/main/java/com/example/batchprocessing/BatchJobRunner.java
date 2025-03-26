package com.example.batchprocessing;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJobRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importUserJob; // Your batch job bean

    @Bean
    public CommandLineRunner runJob() {
        return args -> {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())  // Ensure uniqueness
                    .toJobParameters();

            JobExecution execution = jobLauncher.run(importUserJob, jobParameters);
            System.out.println("Job Execution Status: " + execution.getStatus());
        };
    }
}
