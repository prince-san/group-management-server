package com.princesan.groupmanagementserver.service;

import com.princesan.groupmanagementserver.entity.DTO.CreateGroupDTO;
import com.princesan.groupmanagementserver.entity.DTO.CreateStudentDTO;
import com.princesan.groupmanagementserver.entity.DTO.GroupDTO;
import com.princesan.groupmanagementserver.entity.DTO.GroupListDTO;
import com.princesan.groupmanagementserver.entity.model.Student;
import com.princesan.groupmanagementserver.entity.model.StudentGroup;
import com.princesan.groupmanagementserver.repository.GroupRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final TypeMap<StudentGroup, GroupListDTO> groupGroupListDTOTypeMap;
    private final TypeMap<StudentGroup, GroupDTO> groupGroupDTOTypeMap;
    private final TypeMap<CreateStudentDTO, Student> createStudentDTOStudentTypeMap;

    @Autowired
    public GroupService(GroupRepository groupRepository,
                        TypeMap<StudentGroup, GroupListDTO> groupGroupListDTOTypeMap,
                        TypeMap<StudentGroup, GroupDTO> groupGroupDTOTypeMap,
                        TypeMap<CreateStudentDTO, Student> createStudentDTOStudentTypeMap) {
        this.groupRepository = groupRepository;
        this.groupGroupListDTOTypeMap = groupGroupListDTOTypeMap;
        this.groupGroupDTOTypeMap = groupGroupDTOTypeMap;
        this.createStudentDTOStudentTypeMap = createStudentDTOStudentTypeMap;
    }

    public Page<GroupListDTO> getAllGroups(Pageable pageable) {
        Page<StudentGroup> groupPage = groupRepository.findAll(pageable);
        return new PageImpl<>(
                groupPage.stream().map(groupGroupListDTOTypeMap::map).collect(Collectors.toList()),
                groupPage.getPageable(),
                groupPage.getTotalPages());
    }

    public void createGroup(CreateGroupDTO createGroupDTO) {
        StudentGroup group = new StudentGroup();
        group.setGroupNumber(createGroupDTO.getGroupNumber());
        groupRepository.save(group);
    }

    public GroupDTO getGroup(String groupNumber) {
        return groupGroupDTOTypeMap.map(groupRepository.findByGroupNumber(groupNumber));
    }

    public void addStudentToGroup(String groupNumber, CreateStudentDTO createStudentDTO) {
        Student student = createStudentDTOStudentTypeMap.map(createStudentDTO);
        student.setEnrollmentDate(new Date(System.currentTimeMillis()));
        StudentGroup group = groupRepository.findByGroupNumber(groupNumber);
        student.setStudentGroup(group);
        group.getStudentList().add(student);
        groupRepository.save(group);
    }
}
