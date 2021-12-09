package com.project.scheduler.controllers;

import com.project.scheduler.entity.*;
import com.project.scheduler.exceptions.UserNotFoundException;
import com.project.scheduler.service.CourseService;
import com.project.scheduler.service.StudentService;
import com.project.scheduler.service.TeacherService;
import com.project.scheduler.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GroupController {
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final UserService userService;

    public GroupController(StudentService studentService, TeacherService teacherService, CourseService courseService, UserService userService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/new-groups")
    public String addGroupCourse(Principal principal,
                                 Model model,
                                 @RequestParam(value = "courseId", required = false) Long courseId){
        User user = userService.findByEmail(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        List<Course> courses = courseService.findNotAttendedCourses(user.getUserId());
        model.addAttribute("courses", courses);
        model.addAttribute("user", user);
        model.addAttribute("courseId", courseId);
        List<GroupCourse> groupCourses = null;
        Course course;
        if (courseId != null) {
            course = courseService.findCourseById(courseId).get();
            groupCourses = new ArrayList<>(courseService.findAllGroupsForCourse(course));
            model.addAttribute("course", course.getName());
        }
        model.addAttribute("groupCourses", groupCourses);
        return "addGroup";
    }

    @GetMapping("/new-groups/add")
    public String addGroup(Principal principal,
                           @RequestParam(name="inputSelect") byte group,
                           @RequestParam(name="courseId") Long courseId) {
        User user = userService.findByEmail(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        if (user.getRole() == Role.STUDENT)
            studentService.addGroupForUser(
                    user.getUserId(),
                    courseService.findCourseById(courseId).get(),
                    group);
        else if (user.getRole() == Role.TEACHER)
            teacherService.addGroupForUser(
                    user.getUserId(),
                    courseService.findCourseById(courseId).get(),
                    group);
        return "redirect:/new-groups";
    }

    @Operation(summary = "View my groups")
    @GetMapping("/my-groups")
    public String viewGroups(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        List<GroupCourse> groupCourseList = courseService.findGroupCoursesByEducationUserId(user.getUserId());
        model.addAttribute("groupCourseList", groupCourseList);
        model.addAttribute("user", user);
        return "myGroups";
    }

    @Operation(summary = "Leave group")
    @GetMapping("/my-groups/delete-group")
    public String deleteGroupCourse(Principal principal, @RequestParam Long id){
        User user = userService.findByEmail(principal.getName()).orElseThrow(() -> new UserNotFoundException(principal.getName()));
        GroupCourse groupCourse = courseService.findGroupById(id);
        if (user.getRole() == Role.STUDENT)
            studentService.deleteGroupForUserByGroupCourse(
                    user.getUserId(), groupCourse);
        else if (user.getRole() == Role.TEACHER)
            teacherService.deleteGroupForUserByGroupCourse(
                    user.getUserId(), groupCourse);
        return "redirect:/my-groups";
    }
}
