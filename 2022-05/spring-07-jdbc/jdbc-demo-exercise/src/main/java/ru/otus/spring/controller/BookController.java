package ru.otus.spring.controller;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BooksDao booksDao;
    List<Authors> author = new ArrayList();

    public BookController(BooksDao booksDao) {
        this.booksDao = booksDao;
    }

    @GetMapping({"/"})
    public String indexPage(Model model) {
        return "index";
    }

    @PostFilter("hasAnyRole('USER','ADMIN','STAFF')")
    @GetMapping({"/list"})
    public String listPage(Model model) {
        List<Books> books = booksDao.findAll();
        author = books.stream().map(e -> e.getAuthors()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        model.addAttribute("books", books);
        return "list";
    }
    @PostFilter("hasAnyRole('STAFF','ADMIN')")
    @GetMapping({"/edit"})
    public String editPage(@RequestParam("id") Long id, Model model) {
        final var book = booksDao.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("authors", author);
        model.addAttribute("book", book);
        return "edit";
    }
    @PostFilter("hasAnyRole('STAFF','ADMIN')")
    @GetMapping({"/insert"})
    public String insertPage(Model model) {
        Books book = new Books();
        model.addAttribute("book", book);
        model.addAttribute("authors", this.author);
        return "insert";
    }
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @Validated
    @PostMapping({"/insert"})
    public String saveInsert(@ModelAttribute("book") @Valid BookDto book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "insert";
        } else {
            this.booksDao.save(book.toDomainObject());
            return "redirect:/";
        }
    }
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    @Validated
    @PostMapping({"/edit"})
    public String saveBook(@ModelAttribute("book") @Valid BookDto book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            this.booksDao.save(book.toDomainObject());
            return "redirect:/";
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping({"/bookDelete"})
    public String empDelete(@ModelAttribute("book2") BookDto books, Model model) {
        this.booksDao.delete(books.toDomainObject());
        return "redirect:/";
    }
}
