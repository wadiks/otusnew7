package ru.otus.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;
import ru.otus.spring.service.BooksService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BooksService booksService;
    List<Authors> author = new ArrayList();

    public BookController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping({"/"})
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping({"/list"})
    public String listPage(Model model) {
        List<Books> books = booksService.findAll();
        author = books.stream()
                .filter(e -> e.getAuthors() != null)
                .map(e -> e.getAuthors())
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping({"/edit"})
    public String editPage(@RequestParam("id") Long id, Model model) {
        final var book = booksService.findById(id);
        model.addAttribute("authors", author);
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping({"/insert"})
    public String insertPage(Model model) {
        Books book = new Books();
        model.addAttribute("book", book);
        model.addAttribute("authors", this.author);
        return "insert";
    }

    @Validated
    @PostMapping({"/insert"})
    public String saveInsert(@ModelAttribute("book") @Valid BookDto book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "insert";
        } else {
            this.booksService.save(book.toDomainObject());
            return "redirect:/";
        }
    }

    @Validated
    @PostMapping({"/edit"})
    public String saveBook(@ModelAttribute("book") @Valid BookDto book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            this.booksService.save(book.toDomainObject());
            return "redirect:/";
        }
    }

    @RequestMapping({"/bookDelete"})
    public String empDelete(@ModelAttribute("book2") BookDto books, Model model) {
        this.booksService.delete(books.toDomainObject());
        return "redirect:/";
    }
}
