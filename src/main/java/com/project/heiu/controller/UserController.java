package com.project.heiu.controller;
import com.project.heiu.domain.users.User;
import com.project.heiu.domain.users.UserService;
import com.project.heiu.domain.users.dto.ChangePasswordRequest;
import com.project.heiu.domain.users.dto.UserPrivateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("heiu/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserPrivateResponse> getMe(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.findUser(user.getId()));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMe(@AuthenticationPrincipal User user) {
        userService.delete(user.getId());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/me/password")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal User user, @RequestBody ChangePasswordRequest request){
        userService.changePassword(user.getId(), request.oldPassword(), request.newPassword());
        return ResponseEntity.ok("Password Updated Successfully");
    }

}
