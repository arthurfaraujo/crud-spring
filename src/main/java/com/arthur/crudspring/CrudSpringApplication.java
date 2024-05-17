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

      for (int i = 1; i < 11; i++) {
        Course c = new Course();
        c.setName("Course " + i);
        c.setCategory(Category.FRONT_END);
  
        Lesson l1 = new Lesson();
        l1.setName("Lesson 1");
        l1.setYoutubeUrl("6g8G9LhHx3s");
        l1.setCourse(c);
  
        Lesson l2 = new Lesson();
        l2.setName("Lesson 2");
        l2.setYoutubeUrl("6g8G9LhHx3s");
        l2.setCourse(c);
  
        c.getLessons().add(l1);
        c.getLessons().add(l2);
        courseRepository.save(c);
      }


    };
  }

}
