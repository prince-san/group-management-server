package com.princesan.groupmanagementserver.entity.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class StudentDTO {

    private long id;

    private String name;

    private Date enrollmentDate;
}
