package com.project.scheduler.controllers;

import com.project.scheduler.entity.Course;
import com.project.scheduler.entity.GroupCourse;
import com.project.scheduler.exceptions.CourseNotFoundException;
import com.project.scheduler.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/courses")
public class CourseController {

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses(){
        logger.info("Getting all courses");
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        logger.info("Getting course with id {}", id);
        return courseService.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @GetMapping("/{id}/groups")
    public Set<GroupCourse> getAllGroupsForCourse(@PathVariable Long id){
        Course course = courseService.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        logger.info("Getting allGroups for course {}", course);
        return courseService.findAllGroupsForCourse(course);
    }

    @PostMapping
    public Course addCourse(@RequestBody @Valid Course course){
        logger.info("Adding course {}", course);
        return courseService.saveCourse(course);
    }

    @PostMapping("{id}/groups")
    public Course addGroupsToCourse(@PathVariable Long id, @RequestParam byte numberOfGroups){
        Course course = courseService.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        logger.info("Adding {} groups for course {}", numberOfGroups, course);
        return courseService.saveGroupsForCourse(course, numberOfGroups);
    }

    @PutMapping("/{id}")
    public void updateCourseName(@PathVariable Long id, @RequestParam String newName){
        courseService.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        logger.info("Updating course name to {} for course with id {}", newName, id);
        courseService.updateCourseName(newName, id);
    }

    @PutMapping("/{id}/groups")
    public void updateNumberOfGroups(@PathVariable Long id, @RequestParam byte newNumberOfGroups){
        Course course = courseService.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        Set<GroupCourse> groups = courseService.findAllGroupsForCourse(course);
        for (GroupCourse groupCourse : groups){
            courseService.deleteGroupById(groupCourse.getId());
        }
        courseService.saveGroupsForCourse(course, newNumberOfGroups);
        logger.info("Updating group number to {} for course {}", newNumberOfGroups, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        courseService.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        logger.info("Deleting course with id{}", id);
        courseService.deleteCourseById(id);
    }

    @DeleteMapping("{id}/groups")
    public void deleteGroupsForCourse(@PathVariable Long id){
        Course course = courseService.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        courseService.deleteAllGroups(course);
        logger.info("Deleting groups for course {}", course);
    }

}