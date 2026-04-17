package com.loun.booktracker.controller;

import com.loun.booktracker.service.KakaoBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final KakaoBookService kakaoBookService;

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            Object result = kakaoBookService.searchBooks(query);
            model.addAttribute("result", result);
            model.addAttribute("query, query");
        }
        return "search";
    }
}
