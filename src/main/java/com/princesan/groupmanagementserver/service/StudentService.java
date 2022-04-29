package com.princesan.groupmanagementserver.service;

import com.princesan.groupmanagementserver.entity.DTO.CreateStudentDTO;
import com.princesan.groupmanagementserver.entity.DTO.StudentDTO;
import com.princesan.groupmanagementserver.entity.model.Student;
import com.princesan.groupmanagementserver.entity.model.StudentGroup;
import com.princesan.groupmanagementserver.repository.GroupRepository;
import com.princesan.groupmanagementserver.repository.StudentRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final TypeMap<Student, StudentDTO> studentStudentDTOTypeMap;
    private final TypeMap<CreateStudentDTO, Student> createStudentDTOStudentTypeMap;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository, TypeMap<Student, StudentDTO> studentStudentDTOTypeMap, TypeMap<CreateStudentDTO, Student> createStudentDTOStudentTypeMap) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.studentStudentDTOTypeMap = studentStudentDTOTypeMap;
        this.createStudentDTOStudentTypeMap = createStudentDTOStudentTypeMap;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Page<StudentDTO> getStudentByGroupNumber(String groupNumber, Pageable pageable) {
        StudentGroup group = groupRepository.findByGroupNumber(groupNumber);
        Page<Student> studentPage = studentRepository.findByStudentGroupId(group.getId(), pageable);
        return new PageImpl<>(
                studentPage.stream().map(studentStudentDTOTypeMap::map).collect(Collectors.toList()),
                studentPage.getPageable(),
                studentPage.getTotalPages());
    }

    public void createStudent(String groupNumber, CreateStudentDTO createStudentDTO) {
        StudentGroup group = groupRepository.findByGroupNumber(groupNumber);
        Student student = createStudentDTOStudentTypeMap.map(createStudentDTO);
        student.setEnrollmentDate(new Date(System.currentTimeMillis()));
        student.setStudentGroup(group);
        studentRepository.save(student);
    }
}
