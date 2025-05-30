package com.sivalabs.mcpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    
    private final VideoRepository videoRepository;

    public DataInitializer(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public void run(String... args) {
        long count = videoRepository.count();
        if (count > 0) {
            log.info("There are {} videos in the database", count);
            return;
        }
        log.info("Initializing videos data");
        videoRepository.saveAll(List.of(
                new Video("Spring Boot Microservices Complete Tutorial",
                        "https://www.youtube.com/watch?v=ZKQWwCUEABY"),
                new Video("Spring Modulith Crash Course : Building Modular Monoliths using Spring Boot",
                        "https://www.youtube.com/watch?v=FkP2aZiBrhg")
        ));
    }
}
