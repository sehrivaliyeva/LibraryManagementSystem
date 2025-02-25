package project.bookmanagement.service;

import org.springframework.transaction.annotation.Transactional;
import project.bookmanagement.dto.LoanRequestDto;
import project.bookmanagement.dto.LoanResponseDto;
import project.bookmanagement.entity.Loan;

public interface LoanService {


    LoanResponseDto createLoan(LoanRequestDto requestDto);

    @Transactional
    LoanResponseDto returnBook(Long loanId);
}
