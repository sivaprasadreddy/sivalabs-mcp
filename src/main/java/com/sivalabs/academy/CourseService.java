package com.sivalabs.academy;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    private List<Course> courses = new ArrayList<>();

    @Tool(name = "sl_get_courses", description = "Get a list of courses from SivaLabs")
    public List<Course> getCourses() {
        return courses;
    }

    @Tool(name = "sl_get_course", description = "Get a single course from SivaLabs by title")
    public Course getCourse(String title) {
        return courses.stream()
            .filter(course -> course.title().equals(title))
            .findFirst()
            .orElse(null);
    }

    @PostConstruct
    public void init() {
        courses.addAll(List.of(
            new Course("Spring Boot Microservices Complete Tutorial",
                      "https://www.youtube.com/watch?v=ZKQWwCUEABY"),
            new Course("Spring Modulith Crash Course : Building Modular Monoliths using Spring Boot",
                      "https://www.youtube.com/watch?v=FkP2aZiBrhg")
        ));
    }
}