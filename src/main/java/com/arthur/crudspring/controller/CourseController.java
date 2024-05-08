package com.arthur.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arthur.crudspring.dto.CourseDTO;
import com.arthur.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated // pra fazer a validação funcionar (menos o @Valid)
@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService courseService;

  public CourseController(CourseService courseService) {
    this.courseService = courseService;
  }

  @GetMapping
  public List<CourseDTO> getAllCourses() {
    return courseService.getAllCourses();
  }

  @GetMapping(path = "/{id}")
  public CourseDTO getById(@PathVariable @NotNull @Positive long id) {
    return courseService.getById(id);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public CourseDTO create(@RequestBody @Valid @NotNull CourseDTO course) {
    return courseService.create(course);
  }

  @PutMapping(path = "/{id}")
  public CourseDTO edit(@PathVariable @NotNull @Positive long id, @RequestBody @Valid @NotNull CourseDTO course) {
    return courseService.edit(id, course);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @NotNull @Positive long id) {
    courseService.delete(id);
  }
}
