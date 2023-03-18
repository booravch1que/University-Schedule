package com.zapototsky.webCourse.controller;

import com.zapototsky.webCourse.model.*;
import com.zapototsky.webCourse.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    DisciplineRepository disciplineRepository;
    @Autowired
    TeacherRepository teacherRepository;


    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable Long id){
        return scheduleRepository.findById(id).get();
    }
    @GetMapping()
    public List<Schedule> getFullSchedule(){
        return scheduleRepository.findAll();
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id,@RequestBody ScheduleMapper scheduleMapper){
        Schedule schedule = buildSchedule(scheduleMapper);
        Schedule foundedSchedule = scheduleRepository.findById(id).get();
        foundedSchedule.setClassroom(schedule.getClassroom());
        foundedSchedule.setDiscipline(schedule.getDiscipline());
        foundedSchedule.setTeacher(schedule.getTeacher());
        foundedSchedule.setGroup(schedule.getGroup());
        foundedSchedule.setTime(schedule.getTime());
        return scheduleRepository.save(foundedSchedule);

    }
    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Long id){
        scheduleRepository.deleteById(id);
        return "DELETED SCHEDULE WITH ID:" + id;
    }
    @PostMapping("/")
    public Schedule createSchedule(@RequestBody ScheduleMapper scheduleMapper){
     Schedule schedule = buildSchedule(scheduleMapper);
        if(scheduleRepository.isExist(schedule)) {
            return scheduleRepository.find(schedule);
        }
        return scheduleRepository.save(schedule);
    }
    public Schedule buildSchedule(ScheduleMapper scheduleMapper){
        Schedule schedule = new Schedule();
        schedule.setTime(scheduleMapper.getTime());
        schedule.setClassroom(scheduleMapper.getClassroom());
        schedule.setDiscipline(disciplineRepository.findById(
                scheduleMapper.getDisciplineID()).get());
        schedule.setTeacher(teacherRepository.findById(
                scheduleMapper.getTeacherID()).get());
        schedule.setGroup(groupRepository.findById(
                scheduleMapper.getGroupID()).get());

        return schedule;
    }
}
