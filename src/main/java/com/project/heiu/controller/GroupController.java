package com.project.heiu.controller;

import com.project.heiu.domain.groups.GroupService;
import com.project.heiu.domain.groups.dto.GroupRequest;
import com.project.heiu.domain.groups.dto.GroupResponse;
import com.project.heiu.domain.groups.dto.GroupUpdate;
import com.project.heiu.domain.users.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResponse> getiGroup(@AuthenticationPrincipal User user, @PathVariable UUID groupId){
        return ResponseEntity.ok(groupService.getGroupById(groupId, user.getId()));
    }

    @PatchMapping("/{groupId}")
    public ResponseEntity<String> editGroup(@AuthenticationPrincipal User user, @PathVariable UUID groupId, @RequestBody @Valid GroupUpdate request) {
        groupService.editGroup(user.getId(), groupId, request);
        return ResponseEntity.ok("Group Edited Successfully");
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<String> deleteGroup(@AuthenticationPrincipal User user, @PathVariable UUID groupId) {
        groupService.delete(user.getId(), groupId);
        return ResponseEntity.ok("Group Deleted Successfully");
    }

}
