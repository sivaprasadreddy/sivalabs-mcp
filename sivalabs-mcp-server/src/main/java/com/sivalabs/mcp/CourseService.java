package com.sivalabs.mcp;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseService.class);

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Tool(name = "getCourses", description = "Get a list of courses from SivaLabs")
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Tool(name = "getCourse", description = "Get a single course from SivaLabs by title")
    public Course getCourse(String title) {
        return courseRepository.findByTitle(title).orElse(null);
    }

    @PostConstruct
    public void init() {
        // Only add initial data if the repository is empty
        if (courseRepository.count() == 0) {
            log.info("Initializing courses data");
            courseRepository.saveAll(List.of(
                new Course("Spring Boot Microservices Complete Tutorial",
                          "https://www.youtube.com/watch?v=ZKQWwCUEABY"),
                new Course("Spring Modulith Crash Course : Building Modular Monoliths using Spring Boot",
                          "https://www.youtube.com/watch?v=FkP2aZiBrhg")
            ));
        }
    }
}
