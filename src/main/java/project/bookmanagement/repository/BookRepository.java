package project.bookmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookmanagement.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Object> findByTitle(String bookTitle);

    Optional<Book> findByTitleIgnoreCase(String title);

    Page<Book> findAllByDeletedFalse(Pageable pageable);
}

