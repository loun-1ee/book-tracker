package com.loun.booktracker.controller;

import com.loun.booktracker.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StatsController {

    private final BookService bookService;

    @GetMapping("/stats")
    public String stats(Model model) {
        model.addAttribute("readThisYear", bookService.countReadThisYear());
        model.addAttribute("totalDone", bookService.countDone());
        model.addAttribute("averageRating", bookService.averageRating());
        model.addAttribute("genreStats", bookService.countByGenre());
        return "stats";
    }
}
