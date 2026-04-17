package com.loun.booktracker.repository;

import com.loun.booktracker.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(Book.ReadStatus status);
}