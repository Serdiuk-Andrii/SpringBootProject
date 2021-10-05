package com.project.scheduler.repository;

import com.project.scheduler.entity.Course;
import com.project.scheduler.entity.Lesson;
import com.project.scheduler.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Course,Long> {

    List<Lesson> findAllLessonsByCourse(final Course course);
    List<Lesson> findLessonsByCourseAndGroup(final  Course course, final int group);
    List<Lesson> findLessonsByTeacher(final Teacher teacher);

}