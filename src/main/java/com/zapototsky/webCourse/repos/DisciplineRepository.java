package com.zapototsky.webCourse.repos;


import com.zapototsky.webCourse.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline,Long> {

    default Discipline find(Discipline discipline){
        return this.findAll().stream().filter(x -> x.equals(discipline)).findFirst().orElse(null);
    }
    default boolean isExist(Discipline discipline){
        return this.findAll().stream().anyMatch(x -> x.equals(discipline));
    }
}
