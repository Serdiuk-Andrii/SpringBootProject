package com.project.scheduler;

import com.project.scheduler.entity.Admin;
import com.project.scheduler.entity.Student;
import com.project.scheduler.entity.Teacher;
import com.project.scheduler.entity.User;
import com.project.scheduler.service.UserService;
import com.project.scheduler.service.impl.AdminServiceImpl;
import com.project.scheduler.service.impl.StudentServiceImpl;
import com.project.scheduler.service.impl.TeacherServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupData implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(StartupData.class);


    private final StudentServiceImpl studentService;
    private final TeacherServiceImpl teacherService;
    private final AdminServiceImpl adminService;
    private final UserService userService;



    @Autowired
    public StartupData(StudentServiceImpl studentService,
                       TeacherServiceImpl teacherService,
                       AdminServiceImpl adminService, UserService userService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.adminService = adminService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        logger.trace("This is TRACE");
        logger.debug("This is DEBUG");
        logger.info("This is INFO");
        logger.warn("This is WARN");
        logger.error("This is ERROR");
        adminAccount();
        studentAccount();
        teacherAccount();
        userAccount();
    }

    private void userAccount() {
        User user = new Admin();

        user.setEmail("admin@ukma.edu.ua");
        user.setPassword("admin");

        userService.save(user);
        user.setEmail("YES!");
        userService.update(user);

    }
        private void studentAccount() {
        Student student = new Student();
        student.setEmail("student@ukma.edu.ua");
        student.setFirstName("Ivan");
        student.setLastName("Boyko");
        student.setPassword("student");
        student.setFaculty("FI");
        student.setSpecialty("SE");
        studentService.save(student);
        student.setFaculty("FEN");


        Student student2 = new Student();
        student2.setEmail("student2@ukma.edu.ua");
        student2.setFirstName("Ivan");
        student2.setLastName("Tolkunov");
        student2.setPassword("student2");
        student2.setFaculty("FI");
        student2.setSpecialty("CS");
        studentService.save(student2);

        Student student3 = new Student();

        student3.setEmail("student3@ukma.edu.ua");
        student3.setFirstName("Anna");
        student3.setLastName("Kovalenko");
        student3.setPassword("student3");
        student3.setFaculty("FI");
        student3.setSpecialty("CS");
        studentService.save(student3);

        Student student4 = new Student();
        student4.setEmail("student3@ukma.edu.ua");
        student4.setFirstName("Anna");
        student4.setLastName("Diana");
        student4.setPassword("Melnyk");
        student4.setFaculty("FI");
        student4.setSpecialty("SE");
        studentService.save(student4);

        studentService.delete(student4);
        studentService.updateFaculty(student3, "FH");
        studentService.updateTicketNumber(student2, "121");
        studentService.updateSpeciality(student, "AM");

    }

    private void teacherAccount() {
        Teacher teacher = new Teacher();

        teacher.setEmail("teacher@ukma.edu.ua");
        teacher.setPassword("teacher");
        teacher.setAcademicDegree("Master");
        teacher.setFirstName("Alina");
        teacher.setLastName("Petrivna");
        teacher.setDepartment("FI");
        teacherService.save(teacher);

        Teacher teacher2 = new Teacher();
        teacher2.setEmail("teacher2@ukma.edu.ua");
        teacher2.setPassword("teacher2");
        teacher2.setAcademicDegree("Master");
        teacher2.setFirstName("Maksym");
        teacher2.setLastName("Demchenko");

        teacher2.setDepartment("FI");

        teacherService.save(teacher2);

        teacher2.setEmail("newTeacher2Email@ukma.edu.ua");
        teacher2.setFirstName("New name");

        teacherService.save(teacher2);
        teacherService.delete(teacher);

        //logger.warn("Teacher2`s new email: " + teacherService.findById(teacher2.getUserId()).getEmail());
        teacherService.updateAcademicDegree(teacher2, "PhD");
        teacherService.updateDepartment(teacher2, "Department of Computer Science");
    }

    private void adminAccount() {
        Admin admin = new Admin();

        admin.setEmail("admin@ukma.edu.ua");
        admin.setPassword("admin");

        adminService.saveAdmin(admin);
    }
}