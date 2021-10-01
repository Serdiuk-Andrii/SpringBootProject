package com.project.scheduler;

import com.project.scheduler.entity.Admin;
import com.project.scheduler.entity.Student;
import com.project.scheduler.repository.StudentRepository;
import com.project.scheduler.service.AdminService;
import com.project.scheduler.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupData implements CommandLineRunner {

    private StudentRepository studentRepository;

    @Autowired
    public StartupData(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) {
        adminAccount();
        studentAccount();
    }

    private void studentAccount(){
        Student student = new Student();

        student.setEmail("student@ukma.edu.ua");
        student.setPassword("student");
        studentRepository.save(student);

    }
    private void adminAccount(){
        Admin admin = new Admin();

//        admin.setEmail("admin");
//        admin.setPassword("admin");

        //TODO  masterRepository and uncomment function below
        // masterRepository.save(admin);
    }
}