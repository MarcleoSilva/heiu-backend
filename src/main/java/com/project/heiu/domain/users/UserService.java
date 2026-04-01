package com.project.heiu.domain.users;

import com.project.heiu.domain.users.dto.UserPrivateResponseDTO;
import com.project.heiu.domain.users.dto.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // post
    public UserPrivateResponseDTO register(UserRequestDTO request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already used");
        }

        User user = new User();

        user.setName(request.name());
        user.setUsername(request.username());
        user.setPasswordHash(passwordEncoder.encode(request.passwordHash()));

        User saved = userRepository.save(user);

        return new UserPrivateResponseDTO(saved.getName(), saved.getUsername(), saved.getCreatedAt());
    };

    // get
    public UserPrivateResponseDTO findUser(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserPrivateResponseDTO(user.getName(), user.getUsername(), user.getCreatedAt());
    }

    // delete
    public void delete(UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    // update
    public void changePassword(UUID userId,String oldPassword, String newPassword){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newPasswordHash = passwordEncoder.encode((newPassword));

        if (passwordEncoder.matches(oldPassword, user.getPasswordHash())){
            user.setPasswordHash(newPasswordHash);
        } else {
            throw new RuntimeException("Old password is incorrect");
        }

        userRepository.save(user);

    }

    public void forgotPassword(UUID userId, String newPassword){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newPasswordHash = passwordEncoder.encode((newPassword));

        user.setPasswordHash(newPasswordHash);

        userRepository.save(user);
    }


}
