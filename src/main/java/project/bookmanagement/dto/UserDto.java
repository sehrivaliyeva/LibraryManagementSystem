package project.bookmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;
    private String role;
}
