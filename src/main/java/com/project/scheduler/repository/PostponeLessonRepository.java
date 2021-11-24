package com.project.scheduler.repository;

import com.project.scheduler.entity.PostponeLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostponeLessonRepository extends JpaRepository<PostponeLesson, Long> {
}