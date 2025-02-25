package project.bookmanagement.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.bookmanagement.dto.LoanRequestDto;
import project.bookmanagement.dto.LoanResponseDto;
import project.bookmanagement.entity.Book;
import project.bookmanagement.entity.Loan;
import project.bookmanagement.entity.User;
import project.bookmanagement.exception.LoanNotFoundException;
import project.bookmanagement.repository.BookRepository;
import project.bookmanagement.repository.LoanRepository;
import project.bookmanagement.repository.UserRepository;
import project.bookmanagement.service.LoanService;

import java.time.LocalDate;
import java.util.Collections;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Transactional
    @Override
    public LoanResponseDto createLoan(LoanRequestDto requestDto) {

        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = (Book) bookRepository.findByTitle(requestDto.getBookTitle())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (loanRepository.existsByUserAndBooksContains(user, book)) {
            throw new RuntimeException("User has already borrowed this book");
        }

        long loanedBookCount = loanRepository.countByBooksContains(book);
        if (loanedBookCount > book.getQuantity()) {
            throw new RuntimeException("No more copies available for loan");
        }

        Loan loan = new Loan();
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(LocalDate.now().plusDays(14));
        loan.setUser(user);
        loan.setBooks(Collections.singletonList(book));

        Loan savedLoan = loanRepository.save(loan);

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        LoanResponseDto responseDto = new LoanResponseDto();
        responseDto.setLoanId(savedLoan.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setBookTitle(book.getTitle());
        responseDto.setLoanDate(savedLoan.getLoanDate().toString());

        return responseDto;
    }

    @Transactional
    @Override
    public LoanResponseDto returnBook(Long loanId) {
        Loan loan = (Loan) loanRepository.findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        Book book = loan.getBooks().get(0);

        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        LoanResponseDto responseDto = new LoanResponseDto();
        responseDto.setLoanId(loan.getId());
        responseDto.setUsername(loan.getUser().getUsername());
        responseDto.setBookTitle(book.getTitle());
        responseDto.setLoanDate(loan.getLoanDate().toString());
        responseDto.setLoanDate(loan.getReturnDate().toString());

        return responseDto;
    }

}
