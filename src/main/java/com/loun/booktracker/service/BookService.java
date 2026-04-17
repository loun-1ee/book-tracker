package com.loun.booktracker.service;

import com.loun.booktracker.domain.Book;
import com.loun.booktracker.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    //전체 조회
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    //단건 조회
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("책을 찾을 수 없어요. id=" + id));
    }

    // 저장
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    //삭제
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}