package com.zapototsky.webCourse.repos;

import com.zapototsky.webCourse.model.Student;
import com.zapototsky.webCourse.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    default Student find(Student student){
        return this.findAll().stream().filter(x -> x.equals(student)).findFirst().get();
    }
    default boolean isExist(Student student){
        return this.findAll().stream().anyMatch(x -> x.equals(student));
    }
}
