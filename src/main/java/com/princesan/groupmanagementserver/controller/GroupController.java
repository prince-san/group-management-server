package com.princesan.groupmanagementserver.controller;

import com.princesan.groupmanagementserver.entity.DTO.CreateGroupDTO;
import com.princesan.groupmanagementserver.entity.DTO.CreateStudentDTO;
import com.princesan.groupmanagementserver.entity.DTO.GroupDTO;
import com.princesan.groupmanagementserver.entity.DTO.GroupListDTO;
import com.princesan.groupmanagementserver.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/group")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController (GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    public Page<GroupListDTO> getAllGroups(Pageable pageable) {
        return groupService.getAllGroups(pageable);
    }

    @PostMapping("/")
    public void createGroup(@RequestBody CreateGroupDTO createGroupDTO) {
        groupService.createGroup(createGroupDTO);
    }

    @GetMapping("{group-number}")
    public GroupDTO getGroup(@PathVariable("group-number") String groupNumber) {
        return groupService.getGroup(groupNumber);
    }

    @PutMapping("{group-number}")
    public void addStudentToGroup(@PathVariable("group-number") String groupNumber,
                                  @RequestBody CreateStudentDTO createStudentDTO) {
        System.out.println("Group number: " + groupNumber + ", Student name: " + createStudentDTO.getName());
        groupService.addStudentToGroup(groupNumber, createStudentDTO);
    }
}
