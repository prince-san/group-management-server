package com.princesan.groupmanagementserver.repository;

import com.princesan.groupmanagementserver.entity.model.StudentGroup;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRepository extends PagingAndSortingRepository<StudentGroup, Long> {

    StudentGroup findByGroupNumber(String groupNumber);
}
