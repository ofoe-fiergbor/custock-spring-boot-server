package com.davinci.custockspringboot.controller;

import com.davinci.custockspringboot.domain.dto.auth.SignInDto;
import com.davinci.custockspringboot.domain.dto.auth.SignUpDto;
import com.davinci.custockspringboot.domain.model.auth.User;
import com.davinci.custockspringboot.domain.repository.auth.UserRepository;
import com.davinci.custockspringboot.service.auth.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    @Operation(summary = "Register a new user.")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customUserDetailsService.registerUser(signUpDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Login for existing user.")
    public ResponseEntity<?> login(@RequestBody SignInDto signInDto) {
        Optional<User> user = userRepository.findByUsernameOrEmail(signInDto.getUsernameOrEmail(), signInDto.getUsernameOrEmail());
        if (user.isEmpty()) {
            return new ResponseEntity<>("User with"+ signInDto.getUsernameOrEmail()+" does not exist.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customUserDetailsService.authenticateUser(signInDto, user.get()), HttpStatus.OK);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout of current session.")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>(customUserDetailsService.logout(), HttpStatus.OK);
    }
}
