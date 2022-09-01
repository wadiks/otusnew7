package ru.otus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Books;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private final BooksDao booksDao;

    @Autowired
    public BookController(BooksDao repository) {
        this.booksDao = repository;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Books> books = booksDao.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") Long id, Model model) {
        Books book = booksDao.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/insert")
    public String insertPage(Model model) {
        Books book = new Books();
        model.addAttribute("book", book);
        return "insert";
    }

    @Validated
    @PostMapping("/insert")
    public String saveInsert(@Valid @ModelAttribute("book") BookDto book,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "insert";
        }
        booksDao.save(book.toDomainObject());
        return "redirect:/";
    }

    @Validated
    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto book,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        booksDao.save(book.toDomainObject());
        return "redirect:/";
    }

    @RequestMapping("/bookDelete")
    public String empDelete(@ModelAttribute(value = "book2") BookDto books, Model model) {
        booksDao.delete(books.toDomainObject());
        return "redirect:/";
    }
}
