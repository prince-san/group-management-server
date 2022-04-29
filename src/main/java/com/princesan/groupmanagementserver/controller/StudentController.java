package com.princesan.groupmanagementserver.controller;

import com.princesan.groupmanagementserver.entity.DTO.CreateStudentDTO;
import com.princesan.groupmanagementserver.entity.DTO.StudentDTO;
import com.princesan.groupmanagementserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @DeleteMapping("/")
    public void deleteStudent(@RequestParam("id") Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/")
    public Page<StudentDTO> getStudentByGroupNumber(@RequestParam("group-number") String groupNumber,
                                                    Pageable pageable) {
        return studentService.getStudentByGroupNumber(groupNumber, pageable);
    }

    @PostMapping("/")
    public void createStudent(@RequestParam("group-number") String groupNumber,
                              @RequestBody CreateStudentDTO createStudentDTO) {
        studentService.createStudent(groupNumber, createStudentDTO);
    }
}
