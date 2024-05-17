package com.arthur.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.arthur.crudspring.enums.Category;
import com.arthur.crudspring.model.Course;
import com.arthur.crudspring.model.Lesson;
import com.arthur.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudSpringApplication.class, args);
  }
  
  

  @Bean
  CommandLineRunner initDatabase(CourseRepository courseRepository) {
    return args -> {
      courseRepository.deleteAll();

      Course c = new Course();
      c.setName("Spring Boot BootCamp");
      c.setCategory(Category.BACK_END);

      Lesson l1 = new Lesson();
      l1.setName("Spring Boot Introduction");
      l1.setYoutubeUrl("6g8G9LhHx3s");
      l1.setCourse(c);

      Lesson l2 = new Lesson();
      l2.setName("Spring Boot Configuration");
      l2.setYoutubeUrl("6g8G9LhHx3s");
      l2.setCourse(c);

      c.getLessons().add(l1);
      c.getLessons().add(l2);

      courseRepository.save(c);
    };
  }

}
