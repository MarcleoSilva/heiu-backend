package com.project.heiu.controller;

import com.project.heiu.domain.groups.GroupService;
import com.project.heiu.domain.groups.dto.GroupRequest;
import com.project.heiu.domain.groups.dto.GroupResponse;
import com.project.heiu.domain.users.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("heiu/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<String> createGroup(@AuthenticationPrincipal User user, @RequestBody @Valid GroupRequest request){

        groupService.createGroup(user.getId(), request);
        return ResponseEntity.ok("Group Created Successfully");
    }

    @GetMapping
    public ResponseEntity<List<GroupResponse>> listAllGroups(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(groupService.listGroups(user.getId()));
    }
}
