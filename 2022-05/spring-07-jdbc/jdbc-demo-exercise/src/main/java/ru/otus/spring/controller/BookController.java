package ru.otus.spring.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dao.BooksDao;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.model.Authors;
import ru.otus.spring.model.Books;

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

    @GetMapping({"/list"})
    public String listPage(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication.getPrincipal());
        List<Books> books = this.booksDao.findAll();
        this.author = (List)books.stream().map((e) -> {
            return e.getAuthors();
        }).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping({"/edit"})
    public String editPage(@RequestParam("id") Long id, Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println("edit==" + authentication.getPrincipal());
        Books book = (Books)this.booksDao.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("authors", this.author);
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
            this.booksDao.save(book.toDomainObject());
            return "redirect:/";
        }
    }

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

    @RequestMapping({"/bookDelete"})
    public String empDelete(@ModelAttribute("book2") BookDto books, Model model) {
        this.booksDao.delete(books.toDomainObject());
        return "redirect:/";
    }
}
