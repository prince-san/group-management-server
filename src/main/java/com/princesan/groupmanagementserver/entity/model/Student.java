package com.princesan.groupmanagementserver.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date enrollmentDate;

    @ManyToOne
    private StudentGroup studentGroup;
}
