package com.project.heiu.domain.users;

import com.project.heiu.configuration.JwtService;
import com.project.heiu.domain.users.dto.LoginRequest;
import com.project.heiu.domain.users.dto.LoginResponse;
import com.project.heiu.domain.users.dto.UserPrivateResponse;
import com.project.heiu.domain.users.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // post
    public void register(UserRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already used");
        }

        User user = new User();

        user.setName(request.name());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(user);
    }

    // get
    public UserPrivateResponse findUser(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserPrivateResponse(user.getName(), user.getUsername(), user.getCreatedAt());
    }

    // delete
    public void delete(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    // patch
    public void changePassword(UUID userId,String oldPassword, String newPassword){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newPasswordHash = passwordEncoder.encode((newPassword));

        if (passwordEncoder.matches(oldPassword, user.getPassword())){
            user.setPassword(newPasswordHash);
        } else {
            throw new RuntimeException("Old password is incorrect");
        }

        userRepository.save(user);

    }

    public void forgotPassword(UUID userId, String newPassword){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newPasswordHash = passwordEncoder.encode((newPassword));

        user.setPassword(newPasswordHash);

        userRepository.save(user);
    }

    // login
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }



}
