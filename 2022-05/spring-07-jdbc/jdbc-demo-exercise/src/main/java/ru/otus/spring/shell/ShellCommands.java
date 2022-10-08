package ru.otus.spring.shell;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.sql.DataSource;

@AllArgsConstructor
@ShellComponent
public class ShellCommands {

    private  Job importUserJob;
    private  JobLauncher jobLauncher;
    private DataSource dataSource;


    @ShellMethod(value = "startMigrationJob", key = "start")
    public void startMigrationJobWithJobLauncher() throws Exception {
        JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
                .toJobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "deleteMigration", key = "reset")
    public void resetData() {
        var deleteData = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("deleteData.sql"));
        deleteData.execute(dataSource);
        var  createData = new ResourceDatabasePopulator(false, false, "UTF-8", new ClassPathResource("schema.sql"));
        createData.execute(dataSource);
    }

}
