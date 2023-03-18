package com.zapototsky.webCourse.repos;

import com.zapototsky.webCourse.model.Faculty;
import com.zapototsky.webCourse.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    default Teacher find(Teacher t){
        return this.findAll().stream().filter(x -> x.equals(t)).findFirst().get();
    }
    default boolean isExist(Teacher t){
        return this.findAll().stream().anyMatch(x -> x.equals(t));
    }
}
