package com.loun.booktracker.controller;

import com.loun.booktracker.domain.Book;
import com.loun.booktracker.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    //내 책장 (전체 조회)
    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "ALL") String status,
                        @RequestParam(required = false) Integer rating,
                        Model model) {
        List<Book> books;

        if ("ALL".equals(status)) {
            books = bookService.findAll();
        } else {
            books = bookService.findByStatus(Book.ReadStatus.valueOf(status));
        }

        if ("DONE".equals(status) && rating != null) {
            books = books.stream()
                    .filter(b -> rating.equals(b.getRating()))
                    .collect(java.util.stream.Collectors.toList());
        }

        model.addAttribute("books", books);
        model.addAttribute("status", status);
        model.addAttribute("rating", rating);
        return "index";
    }

    //책 추가 폼
    @GetMapping("/books/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    //책 저장
    @PostMapping("/books")
    public String saveBook(@ModelAttribute Book book) {
        Book saved = bookService.save(book);
        return "redirect:/books/" + saved.getId() + "/edit";
    }

    //책 삭제
    @PostMapping("/books/{id}/delete")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/";
    }

    //수정 폼
    @GetMapping("/books/{id}/edit")
    public String editBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book-edit";
    }

    //수정 저장
    @PostMapping("/books/{id}/edit")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        Book existing = bookService.findById(id);
        existing.setStatus(book.getStatus());
        existing.setRating(book.getRating());
        existing.setMemo(book.getMemo());
        existing.setGenre(book.getGenre());
        existing.setAddedDate(book.getAddedDate());
        bookService.save(existing);
        return "redirect:/";
    }

    //상세 조회
    @GetMapping("/books/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book-detail";
    }
}
