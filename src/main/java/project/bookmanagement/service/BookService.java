package project.bookmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.bookmanagement.dto.BookResponseDto;
import project.bookmanagement.dto.CreateBookDto;
import project.bookmanagement.entity.Book;

import java.util.List;

public interface BookService {
    BookResponseDto addBook(CreateBookDto createBookDto);

    BookResponseDto getBookById(long id);

    Page<BookResponseDto> getAllBooks(Pageable pageable);

    BookResponseDto updateBook(Long id, CreateBookDto bookRequestDto);


    void deleteBook(Long id);
}
