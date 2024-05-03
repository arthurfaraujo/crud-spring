package com.arthur.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.arthur.crudspring.model.Course;
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
  public List<Course> getAllCourses() {
    return courseService.getAllCourses();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Course> getById(@PathVariable @NotNull @Positive long id) {
    return courseService.getById(id).map(ResponseEntity.ok()::body)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Course create(@RequestBody @Valid Course course) {
    return courseService.create(course);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Course> edit(@PathVariable @NotNull @Positive long id, @RequestBody @Valid Course course) {
    return courseService.edit(id, course).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive long id) {
    return courseService.delete(id) ? ResponseEntity.noContent().<Void>build() : ResponseEntity.notFound().build();
  }
}
