package com.project.scheduler.service;

import com.project.scheduler.entity.PostponeLesson;
import com.project.scheduler.entity.ScheduleDate;
import com.project.scheduler.entity.Teacher;

import java.util.List;

public interface PostponeLessonService {

    PostponeLesson postponeLessonTimeAndDescription(long id, ScheduleDate newDate,String description);
    PostponeLesson postponeLessonTimePlaceAndDescription(long id,ScheduleDate newDate,String description,String newPlace);
    List<PostponeLesson> getAllRequests();
}