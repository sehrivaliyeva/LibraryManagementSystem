package project.bookmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.bookmanagement.dto.LoanRequestDto;
import project.bookmanagement.dto.LoanResponseDto;
import project.bookmanagement.service.LoanService;
import project.bookmanagement.service.impl.LoanServiceImpl;

@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    @Autowired
    LoanService loanService;
    @Autowired
    private LoanServiceImpl loanServiceImpl;

    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(@RequestBody LoanRequestDto requestDto) {
        LoanResponseDto response = loanService.createLoan(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<String> returnLoan(@PathVariable Long id) {
        loanServiceImpl.returnBook(id);
        return ResponseEntity.ok("Book successfully returned");
    }

}
