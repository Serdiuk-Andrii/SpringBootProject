package com.project.scheduler;

import com.project.scheduler.entity.Admin;
import com.project.scheduler.entity.Teacher;
import com.project.scheduler.repository.AdminRepository;
import com.project.scheduler.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@DataJpaTest
public class TeacherJPATest {

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    void shouldReturnTrueWhenRepoIsNotEmpty() {
        List<Teacher> teachers = teacherRepository.findAll();
        assertNotNull(teachers);
    }

    @Test
    void shouldReturnTrueWhenRepoIsEmpty() {
        List<Teacher> teachers = any();
        assertNull(teachers);
    }


    @Test
    public void teachersTest() {
        Teacher teacher = new Teacher();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        teacher.setEmail("teacher@ukma.edu.ua");
        teacher.setPassword(encoder.encode("teacher"));
        teacher.setAcademicDegree("Master");
        teacher.setFirstName("Alina");
        teacher.setLastName("Petrivna");
        teacher.setDepartment("FI");
        teacher.setRole("TEACHER");
        teacher.setAuthorized(true);
        teacherRepository.save(teacher);
        Iterable<Teacher> teachers = teacherRepository.findAll();
        assertThat(teachers).hasSize(1);
    }

    @Test
    void shouldReturnTrueIfNameIsAlina(){
        Teacher teacher = new Teacher();
        teacher.setFirstName("Alina");
        assertEquals(teacher.getFirstName(),"Alina");
    }
}


