package project.bookmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanResponseDto {
    private Long loanId;
    private String username;
    private String bookTitle;
    private String loanDate;
}
