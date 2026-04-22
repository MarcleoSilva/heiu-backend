package com.project.heiu.domain.groups;

import com.project.heiu.domain.groups.dto.GroupRequest;
import com.project.heiu.domain.groups.dto.GroupResponse;
import com.project.heiu.domain.groups.dto.GroupUpdate;
import com.project.heiu.domain.users.User;
import com.project.heiu.domain.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    // custom methods
    private GroupResponse mapToResponse(Group group){
        return new GroupResponse(
            group.getId(),
            group.getName(),
            group.getDescription(),
            group.getColor(),
            group.getTags(),
            group.getPhoto(),
            group.getCreatedAt(),
            group.getCard().size()
        );
    }

    //post
    public void createGroup(UUID userId, GroupRequest groupRequest) {
        if (groupRepository.existsByNameAndUserId(groupRequest.name(), userId)){
            throw new RuntimeException("Group already exists");
        }
        Group group = new Group();

        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

        group.setName(groupRequest.name());
        group.setDescription(groupRequest.description());
        group.setColor(groupRequest.color());
        group.setTags(groupRequest.tags());
        group.setPhoto(groupRequest.photo());
        group.setUser(user);

        groupRepository.save(group);
    }

    //get all
    public List<GroupResponse> listGroups(UUID userId){

        if (!userRepository.existsById(userId)){
            throw new RuntimeException("User not found");
        }

        return groupRepository.findAllByUserId(userId)
                .stream()
                .map(group -> new GroupResponse(
                        group.getId(),
                        group.getName(),
                        group.getDescription(),
                        group.getColor(),
                        group.getTags(),
                        group.getPhoto(),
                        group.getCreatedAt(),
                        group.getCard().size()
                ))
                .toList();
    }

    // get one
    public GroupResponse getGroupById(UUID groupId, UUID userId){
        Group group = groupRepository.findByIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        return mapToResponse(group);
    }

    public GroupResponse getGroupByName(String name, UUID userId){
        Group group = groupRepository.findByNameAndUserId(name, userId)
                .orElseThrow(() -> new RuntimeException("Name not found"));

        return mapToResponse(group);
    }

    // update
    @Transactional
    public void editGroup(UUID userId, UUID groupId, GroupUpdate data) {
        Group group = groupRepository.findByIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Group not found or unauthorized"));

        if (data.name() != null) group.setName(data.name());
        if (data.description() != null) group.setDescription(data.description());
        if (data.color() != null) group.setColor(data.color());
        if (data.tags() != null) group.setTags(data.tags());
        if (data.photo() != null) group.setPhoto(data.photo());

    }

    // delete
    public void delete(UUID userId, UUID groupId) {
        Group group = groupRepository.findByIdAndUserId(groupId, userId)
                .orElseThrow(() -> new RuntimeException("Group not found or unauthorized"));
        groupRepository.delete(group);
    }

}
