package project.bookmanagement.service.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.bookmanagement.dto.BookResponseDto;
import project.bookmanagement.dto.CreateBookDto;
import project.bookmanagement.entity.Book;
import project.bookmanagement.exception.BookNotFoundException;
import project.bookmanagement.repository.BookRepository;
import project.bookmanagement.service.BookService;

import java.util.List;

@Service

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addBook(CreateBookDto createBookDto) {
        Book book = modelMapper.map(createBookDto, Book.class);
        bookRepository.save(book);
    }


    @Override
    public BookResponseDto getBookById(long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found with id : " + id));
        BookResponseDto bookResponseDto = modelMapper.map(book, BookResponseDto.class);
        return bookResponseDto;

    }

    //burada pagable istifade olunub
    //hem de deleted statusu 0 olan yeni silinmeyen kitablar gosterilir
    public Page<BookResponseDto> getAllBooks(Pageable pageable) {
        Page<Book> booksPage = bookRepository.findAllByDeletedFalse(pageable); // Yalnız deleted = false olanlar
        List<BookResponseDto> bookDtos = modelMapper.map(booksPage.getContent(), new TypeToken<List<BookResponseDto>>() {}.getType());
        return new PageImpl<>(bookDtos, pageable, booksPage.getTotalElements());
    }

    @Override
    public BookResponseDto updateBook(Long id, CreateBookDto createBookDto) {

        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id : " + id));
        modelMapper.map(createBookDto, existingBook);
        Book updatedBook = bookRepository.save(existingBook);
        return modelMapper.map(updatedBook, BookResponseDto.class);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        book.setDeleted(true);
        bookRepository.save(book);
    }


}
