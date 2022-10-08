package ru.otus.spring.service;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.otus.spring.modelMongo.CommentMongo;
import ru.otus.spring.modelSql.CommentM2;

@Component
public class ConverterService implements ItemProcessor<CommentMongo, CommentM2> {
    @Override
    public CommentM2 process(CommentMongo commentMongo) {
        final CommentM2 m2 = CommentM2.builder()
                .kText(commentMongo.getKtext())
                .id(commentMongo.getBook_id().getId())
                .build();
        return m2;
    }
}
