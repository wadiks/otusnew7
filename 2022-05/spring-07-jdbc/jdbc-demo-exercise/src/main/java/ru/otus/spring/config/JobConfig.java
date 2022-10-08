package ru.otus.spring.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.MongoItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.dao.jpa.CommentDaoJpa;
import ru.otus.spring.modelMongo.CommentMongo;
import ru.otus.spring.modelSql.CommentM2;

import java.util.HashMap;

@Configuration
public class JobConfig {
    private static final int CHUNK_SIZE = 5;
    public static final String IMPORT_JOB_NAME = "importJob";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CommentDaoJpa commentDaoJpa;


    @Bean
    public Job importUserJob(Step transformPersonsStep, Step cleanUpStep) {
        return jobBuilderFactory.get(IMPORT_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(transformPersonsStep)
                .end()
                .build();
    }

    @Bean
    public Step transformPersonsStep(ItemReader<CommentMongo> reader, RepositoryItemWriter<CommentM2> writer,
                                     ItemProcessor<CommentMongo, CommentM2> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<CommentMongo, CommentM2>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public MongoItemReader<CommentMongo> reader(MongoTemplate mongoTemplate) {
        HashMap<String, Sort.Direction> sortMap = new HashMap<>();
        sortMap.put("id", Sort.Direction.DESC);
        return new MongoItemReaderBuilder<CommentMongo>()
                .name("MongoItemReader")
                .template(mongoTemplate)
                .jsonQuery("{}")   // выбрать все
                .targetType(CommentMongo.class)
                .sorts(sortMap)
                .build();
    }

    @Bean
    public RepositoryItemWriter<CommentM2> Writer() {
        RepositoryItemWriter<CommentM2> peopleRepositoryItemWriter = new RepositoryItemWriter<>();
        peopleRepositoryItemWriter.setRepository(commentDaoJpa);
        peopleRepositoryItemWriter.setMethodName("save");
        return peopleRepositoryItemWriter;
    }

}
