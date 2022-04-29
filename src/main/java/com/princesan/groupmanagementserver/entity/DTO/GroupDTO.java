package com.princesan.groupmanagementserver.entity.DTO;

import lombok.Data;

import java.util.List;

@Data
public class GroupDTO {

    private String groupNumber;

    private List<StudentDTO> studentDTOList;
}