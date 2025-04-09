package com.sivalabs.academy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    /**
     * Find a course by its title
     * 
     * @param title the title of the course
     * @return an Optional containing the course if found, or empty if not found
     */
    Optional<Course> findByTitle(String title);
}