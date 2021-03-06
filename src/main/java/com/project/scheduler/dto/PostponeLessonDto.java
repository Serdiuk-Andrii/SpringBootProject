package com.project.scheduler.dto;

import com.project.scheduler.entity.Lesson;
import com.project.scheduler.entity.PostponeStatus;
import com.project.scheduler.entity.ScheduleDate;
import com.project.scheduler.entity.PostponeLesson;
import lombok.Data;

import java.util.stream.Collectors;

@Data
public class PostponeLessonDto {

    private String lesson;
    private String lessonType;
    private String description;
    private String teacher;
    private String oldDate;
    private ScheduleDate newDate;
    private Long id;
    private Long canceledId;
    private PostponeStatus status;

    public PostponeLessonDto(PostponeLesson postponeLesson){
        Lesson canceled = postponeLesson.getCanceledLesson();
        this.lesson = canceled.getGroupCourse().getCourse().getName();
        this.lessonType = canceled.getType().getType();
        this.description = postponeLesson.getDescription();
        this.teacher = canceled.getGroupCourse().getTeachers().stream().map(x -> x.getFirstName() + x.getLastName()).collect(Collectors.joining());
        this.oldDate = postponeLesson.getOldDate();
        this.newDate = postponeLesson.getNewDate();
        this.id = postponeLesson.getLessonId();
        this.status = postponeLesson.getStatus();
        this.canceledId = postponeLesson.getCanceledLesson().getLessonId();
    }
}
