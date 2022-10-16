package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.model.Books;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableHystrix
public class BooksService {

    private final BooksDao booksDao;

    public BooksService(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    @HystrixCommand(commandKey = "findAllCommand", fallbackMethod = "findAllHystrix", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public List<Books> findAll() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return booksDao.findAll();
    }

    @HystrixCommand(commandKey = "findByIdCommand", fallbackMethod = "booksHystrix", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public Books findById(Long id) {
        return booksDao.findById(id).get();
    }

    public void save(Books books) {
        booksDao.save(books);
    }

    public void delete(Books books) {
        booksDao.delete(books);
    }


    public List<Books> findAllHystrix() {
        List<Books> books = new ArrayList<>();
        books.add(booksHystrix());
        return books;
    }

    public Books booksHystrix() {
        return new Books() {{
            setId(0L);
            setName("NA");
        }};
    }

}
