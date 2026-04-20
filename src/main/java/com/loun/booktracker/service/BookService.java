package com.loun.booktracker.service;

import com.loun.booktracker.domain.Book;
import com.loun.booktracker.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    //탭
    public List<Book> findByStatus(Book.ReadStatus status) {
        return bookRepository.findByStatus(status);
    }

    //저장
    public Book save(Book book) {
        if (book.getAddedDate() == null) {
            book.setAddedDate(LocalDate.now());
        }
        return bookRepository.save(book);
    }

    //삭제
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    //올해 읽은 책
    public long countReadThisYear() {
        int year = LocalDate.now().getYear();
        return bookRepository.findAll().stream()
                .filter(b -> b.getStatus() == Book.ReadStatus.DONE)
                .filter(b -> b.getAddedDate() != null && b.getAddedDate().getYear() == year)
                .count();
    }

    // 장르별 통계
    public Map<String, Long> countByGenre() {
        return bookRepository.findAll().stream()
                .filter(b -> b.getGenre() != null)
                .collect(java.util.stream.Collectors.groupingBy(
                        b -> b.getGenre().name(),
                        java.util.stream.Collectors.counting()
                ));
    }

    // 전체 읽은 책 수
    public long countDone() {
        return bookRepository.findAll().stream()
                .filter(b -> b.getStatus() == Book.ReadStatus.DONE)
                .count();
    }

    // 평균 별점
    public double averageRating() {
        return bookRepository.findAll().stream()
                .filter(b -> b.getRating() != null)
                .mapToInt(Book::getRating)
                .average()
                .orElse(0.0);
    }
}