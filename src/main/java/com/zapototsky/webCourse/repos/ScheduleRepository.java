package com.zapototsky.webCourse.repos;

import com.zapototsky.webCourse.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    default Schedule find(Schedule schedule){
        return this.findAll().stream()
                .filter(x -> x.equals(schedule))
                .findFirst().get();
    }
    default boolean isExist(Schedule schedule){
        return this.findAll().stream()
                .anyMatch(x -> x.equals(schedule));
    }
    default List<Schedule> findByGroupId(Long id){
        return this.findAll().stream()
                .filter(x -> Objects.equals(x.getGroup().getId(), id))
                .collect(Collectors.toList());
    }
    default List<Schedule> findByDisciplineId(Long id){
        return this.findAll().stream()
                .filter(x -> Objects.equals(x.getDiscipline().getId(), id))
                .collect(Collectors.toList());
    }
    default List<Schedule> findByTeacherId(Long id){
        return this.findAll().stream()
                .filter(x -> Objects.equals(x.getTeacher().getId(), id))
                .collect(Collectors.toList());
    }
    @Query(value = "DELETE FROM public.schedule WHERE id = ?1",nativeQuery = true)
    default void safeDeleteById(Long id){
    }
}
