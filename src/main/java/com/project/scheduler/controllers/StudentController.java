package com.project.scheduler.controllers;

import com.project.scheduler.entity.Course;
import com.project.scheduler.entity.GroupCourse;
import com.project.scheduler.entity.Student;
import com.project.scheduler.entity.User;
import com.project.scheduler.exceptions.CourseNotFoundException;
import com.project.scheduler.exceptions.GroupNotFoundException;
import com.project.scheduler.service.StudentService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);
    Marker myMarker = MarkerFactory.getMarker("StudentClassMarker");
    @Autowired
    StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        logger.info(myMarker, "Getting course with id {}", id);
        return studentService.findById(id);
    }




    @PostMapping
    public Student addStudent(@RequestBody Student student){
        logger.info(myMarker, "Adding student {}", student);
        return studentService.save(student);
    }



    @PutMapping("/{id}")
    public void updateStudentName(@PathVariable Long id, @RequestParam String newFaculty){
       Student student= studentService.findById(id);
        logger.info(myMarker, "Updating student course to {} for course with id {}", newFaculty, id);
        studentService.updateFaculty(student,newFaculty);
    }





    @DeleteMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable long id){
        Student student =studentService.findById(id);

        studentService.delete(student);
        return" new student deleted";
    }
}