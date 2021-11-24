package com.project.scheduler.repository;

import com.project.scheduler.entity.GroupCourse;
import com.project.scheduler.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {

//    @Query(value = "SELECT s.lessons FROM GroupCourse s JOIN s.lessons WHERE s.id = :id")
    List<Lesson> findLessonsByGroupCourse(GroupCourse groupCourse);

//    List<Lesson>

}
