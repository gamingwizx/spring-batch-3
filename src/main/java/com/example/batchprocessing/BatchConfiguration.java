package com.example.batchprocessing;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {
    private final DataSource dataSource;

    public BatchConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public JdbcCursorItemReader<InputPerson> reader(DataSource dataSource) {
        System.out.println("Reading data from database...");
        return new JdbcCursorItemReaderBuilder<InputPerson>()
                .dataSource(dataSource)
                .name("input_person_reader")
                .sql("SELECT * FROM input_user")
                .rowMapper(new CustomerCreditRowMapper())
                .build();
    }

    @Bean
    public ItemProcessor<InputPerson, OutputPerson> processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<OutputPerson> writer(DataSource dataSource) {
        System.out.println("Writing records into file...");
//        BeanWrapperFieldExtractor<OutputPerson> fieldExtractor = new BeanWrapperFieldExtractor<>();
//        fieldExtractor.setNames(new String[]{"firstName", "lastName", "typicalHours", "annualSalary", "hourlyRate", "monthlySalary"});
//        fieldExtractor.afterPropertiesSet();

        return new JdbcBatchItemWriterBuilder<OutputPerson>()
                .dataSource(dataSource)
                .sql("INSERT INTO output_user (first_name, last_name, work_hours, annual_salary, hourly_rate) VALUES (:firstName, :lastName, :work_hours, :annual_salary, :hourly_rate)")
                .itemSqlParameterSourceProvider(user -> {
                    MapSqlParameterSource paramSource = new MapSqlParameterSource();
                    paramSource.addValue("firstName", user.getFirstName());
                    paramSource.addValue("lastName", user.getLastName());
                    paramSource.addValue("work_hours", user.getTypicalHours());
                    paramSource.addValue("annual_salary", user.getAnnualSalary());
                    paramSource.addValue("hourly_rate", user.getHourlyRate());
//                    paramSource.addValue("monthly_salary", user.getMonthlySalary());

                    return paramSource;
                })
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager, JdbcCursorItemReader<InputPerson> reader, ItemProcessor<InputPerson, OutputPerson> processor, JdbcBatchItemWriter<OutputPerson> writer) {
        return new StepBuilder("step1", jobRepository)
                .<InputPerson, OutputPerson>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}