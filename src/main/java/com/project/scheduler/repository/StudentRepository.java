package com.project.scheduler.repository;

import com.project.scheduler.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findById(long id);
    Student findByEmail(String email);
    Student findByLastName(String lastname);


}