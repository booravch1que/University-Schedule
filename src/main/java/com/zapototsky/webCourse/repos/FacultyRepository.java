package com.zapototsky.webCourse.repos;

import com.zapototsky.webCourse.model.Department;
import com.zapototsky.webCourse.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    default Faculty find(Faculty faculty){
        return this.findAll().stream().filter(x -> x.equals(faculty)).findFirst().get();
    }
    default boolean isExist(Faculty faculty){
        return this.findAll().stream().anyMatch(x -> x.equals(faculty));
    }
}
