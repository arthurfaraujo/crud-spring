package com.arthur.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.arthur.crudspring.dto.CourseDTO;
import com.arthur.crudspring.dto.mapper.CourseMapper;
import com.arthur.crudspring.exception.RecordNotFoundException;
import com.arthur.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

  private final CourseRepository courseRepository;
  private final CourseMapper courseMapper;

  public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
    this.courseRepository = courseRepository;
    this.courseMapper = courseMapper;
  }

  public List<CourseDTO> getAllCourses() {
    return courseRepository.findAll().stream().map(courseMapper::toDTO).toList();
  }

  public CourseDTO getById(@NotNull @Positive long id) {
    return courseRepository.findById(id).map(courseMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public CourseDTO create(@Valid @NotNull CourseDTO course) {
    return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
  }

  public CourseDTO edit(@NotNull @Positive long id, @Valid @NotNull CourseDTO course) {
    return courseRepository.findById(id).map(found -> {
      found.setName(course.name());
      found.setCategory(courseMapper.convertCategoryValue(course.category()));
      return courseRepository.save(found);
    }).map(courseMapper::toDTO).orElseThrow(() -> new RecordNotFoundException(id));
  }

  public void delete(@NotNull @Positive long id) {
    courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
  }
}
