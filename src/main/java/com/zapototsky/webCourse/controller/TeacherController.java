package com.zapototsky.webCourse.controller;

import com.zapototsky.webCourse.model.Teacher;
import com.zapototsky.webCourse.repos.ScheduleRepository;
import com.zapototsky.webCourse.repos.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/{id}")
    public Teacher getTeacher(@PathVariable Long id){


        return teacherRepository.findById(id).get();
    }

    @GetMapping()
    public List<Teacher> getTeacher(){
        return teacherRepository.findAll();
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher){
        Teacher foundedTeacher = teacherRepository.findById(id).get();
        foundedTeacher.setName(teacher.getName());
        foundedTeacher.setEmail(teacher.getEmail());
        foundedTeacher.setPhone(teacher.getPhone());
        foundedTeacher.setSurname(teacher.getSurname());
        return teacherRepository.save(foundedTeacher);

    }
    @DeleteMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id){
        scheduleRepository.deleteAll(scheduleRepository.findByTeacherId(id));
        teacherRepository.delete(teacherRepository.findById(id).get());
        return "DELETED";
    }
    @PostMapping("/")
    public Teacher createTeacher(@RequestBody Teacher teacher){
        if(teacherRepository.isExist(teacher)) return teacherRepository.find(teacher);
        return teacherRepository.save(teacher);
    }

}
