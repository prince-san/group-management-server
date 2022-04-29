package com.princesan.groupmanagementserver.configuration;

import com.princesan.groupmanagementserver.entity.DTO.CreateStudentDTO;
import com.princesan.groupmanagementserver.entity.DTO.StudentDTO;
import com.princesan.groupmanagementserver.entity.model.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

@Configuration
public class StudentModelMapperConfiguration {

    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public TypeMap<CreateStudentDTO, Student> getCreateStudentDTOStudentMapper() {
        return modelMapper.createTypeMap(CreateStudentDTO.class, Student.class);
    }

    @Bean
    public TypeMap<Student, StudentDTO> getCreateStudentStudentDTOStudentMapper() {
        return modelMapper.createTypeMap(Student.class, StudentDTO.class);
    }
}
