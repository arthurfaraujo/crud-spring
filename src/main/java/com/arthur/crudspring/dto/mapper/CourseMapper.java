package com.arthur.crudspring.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.arthur.crudspring.dto.CourseDTO;
import com.arthur.crudspring.dto.LessonDTO;
import com.arthur.crudspring.enums.Category;
import com.arthur.crudspring.model.Course;

@Component
public class CourseMapper {

  public CourseDTO toDTO(Course course) {
    if (course == null) {
      return null;
    }

    List<LessonDTO> lessons = course.getLessons().stream()
        .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
        .toList();

    return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue(), lessons);
  }

  public Course toEntity(CourseDTO courseDTO) {
    if (courseDTO == null) {
      return null;
    }

    Course course = new Course();
    course.setId(courseDTO._id());
    course.setName(courseDTO.name());
    course.setCategory(convertCategoryValue(courseDTO.category()));
    return course;
  }

  public Category convertCategoryValue(String value) {
    return Category.fromValue(value);
  }
}
