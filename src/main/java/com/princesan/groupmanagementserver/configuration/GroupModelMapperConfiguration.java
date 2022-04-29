package com.princesan.groupmanagementserver.configuration;

import com.princesan.groupmanagementserver.entity.DTO.GroupDTO;
import com.princesan.groupmanagementserver.entity.DTO.GroupListDTO;
import com.princesan.groupmanagementserver.entity.DTO.StudentDTO;
import com.princesan.groupmanagementserver.entity.model.StudentGroup;
import com.princesan.groupmanagementserver.entity.model.Student;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class GroupModelMapperConfiguration {

    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public TypeMap<StudentGroup, GroupListDTO> getGroupGroupListDTOMapper() {
        TypeMap<StudentGroup, GroupListDTO> typeMap = modelMapper.createTypeMap(StudentGroup.class, GroupListDTO.class);
        Converter<List<Student>, Integer> listToSize = list -> list.getSource().size();
        return typeMap.addMappings(mapper -> mapper.using(listToSize).map(StudentGroup::getStudentList, GroupListDTO::setStudentCount));
    }

    @Bean
    @Autowired
    public TypeMap<StudentGroup, GroupDTO> getGroupGroupDTOMapper(TypeMap<Student, StudentDTO> studentStudentDTOTypeMap) {
        TypeMap<StudentGroup, GroupDTO> typeMap = modelMapper.createTypeMap(StudentGroup.class, GroupDTO.class);
        Converter<List<Student>, List<StudentDTO>> listConverter =
                list -> list.getSource()
                        .stream()
                        .map(studentStudentDTOTypeMap::map)
                        .collect(Collectors.toList());
        return typeMap.addMappings(mapper -> mapper.using(listConverter).map(StudentGroup::getStudentList,
                GroupDTO::setStudentDTOList));
    }
}
