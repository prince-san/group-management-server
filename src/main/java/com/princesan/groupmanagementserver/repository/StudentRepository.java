package com.princesan.groupmanagementserver.repository;

import com.princesan.groupmanagementserver.entity.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    Page<Student> findByStudentGroupId(Long id, Pageable pageable);
}
