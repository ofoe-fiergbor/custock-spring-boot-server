package com.davinci.custockspringboot.service.auth;

import com.davinci.custockspringboot.domain.dto.auth.SignUpDto;
import com.davinci.custockspringboot.domain.dto.auth.SignInDto;
import com.davinci.custockspringboot.domain.model.auth.Role;
import com.davinci.custockspringboot.domain.model.auth.User;
import com.davinci.custockspringboot.domain.repository.auth.RoleRepository;
import com.davinci.custockspringboot.domain.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with the username/ email: "+ usernameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Role role){
        return Collections.singleton(new SimpleGrantedAuthority(role.getName()));
    }

    public String registerUser(SignUpDto signUpDto) {
        Role role = roleRepository.findByName("USER").get();
        User user = new User(
                signUpDto.getFirstName(),
                signUpDto.getLastName(),
                signUpDto.getUsername(),
                signUpDto.getEmail(),
                passwordEncoder.encode(signUpDto.getPassword()),
                role
        );

        userRepository.save(user);
        return "User registered successfully!";
    }

    public User authenticateUser(SignInDto signinDto, User user) {
//        System.err.println(signinDto);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinDto.getUsernameOrEmail(), signinDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return user;
    }

    public String logout(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "User logged out successfully!";
    }
}
