package ru.otus.spring.controller;

import org.springframework.stereotype.Controller;

@Controller
public class BookController {
/*
    private final BooksDao booksDao;

    List<Authors> author = new ArrayList<>();

    public BookController(BooksDao booksDao) {
        this.booksDao = booksDao;
    }


    @GetMapping("/")
    public String listPage(Model model) {
        List<Books> books = booksDao.findAll();
        author = books.stream().map(e-> e.getAuthors()).flatMap(List::stream).distinct().collect(Collectors.toList());
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") Long id, Model model) {
        Books book = booksDao.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("authors", author);
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/insert")
    public String insertPage(Model model) {
        Books book = new Books();
        model.addAttribute("book", book);
        model.addAttribute("authors", author);
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

 */
}
