package project.bookmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookmanagement.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Loan> {
}
