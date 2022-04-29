package com.princesan.groupmanagementserver.entity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class StudentGroup {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentGroup")
    private List<Student> studentList;
}
