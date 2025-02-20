package project.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookDto {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private int pages;
    private String genre;
    private int quantity;
    private boolean deleted;
}
