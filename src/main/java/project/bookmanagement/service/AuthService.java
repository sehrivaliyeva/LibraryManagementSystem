package project.bookmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.bookmanagement.dto.LoginDto;
import project.bookmanagement.dto.UserDto;
import project.bookmanagement.entity.User;
import project.bookmanagement.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final MyUserDetailsService myUserDetailsService;


    public String register(UserDto userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already exists");


        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setRole(userDTO.getRole());
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String login(LoginDto loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(user.getUsername());

        return jwtService.generateToken(userDetails.getUsername());
    }
}
