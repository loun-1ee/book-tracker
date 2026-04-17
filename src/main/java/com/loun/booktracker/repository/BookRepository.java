package com.loun.booktracker.repository;

import com.loun.booktracker.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
