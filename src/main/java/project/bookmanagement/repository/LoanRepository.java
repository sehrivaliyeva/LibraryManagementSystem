package project.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookmanagement.entity.Book;
import project.bookmanagement.entity.Loan;
import project.bookmanagement.entity.User;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Loan> {


    boolean existsByUserAndBooksContains(User user, Book book);

    long countByBooksContains(Book book);

    Optional<Object> findById(Long loanId);
}
