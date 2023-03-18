package com.zapototsky.webCourse.controller;

import com.zapototsky.webCourse.model.Group;
import com.zapototsky.webCourse.model.Schedule;
import com.zapototsky.webCourse.repos.DepartmentRepository;
import com.zapototsky.webCourse.repos.GroupRepository;
import com.zapototsky.webCourse.repos.ScheduleRepository;
import com.zapototsky.webCourse.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;


@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable Long id){
        return groupRepository.findById(id).get();
    }

    @GetMapping()
    public List<Group> getAllGroups(){
        return groupRepository.findAll();
    }

    @PutMapping("/{id}")
    public Group updateGroup(@PathVariable Long id, @RequestBody Group group){
        Group foundedGroup = groupRepository.findById(id).get();
        foundedGroup.setName(group.getName());
        foundedGroup.setStudentList(group.getStudentList());
        foundedGroup.setCourse(group.getCourse());
        return groupRepository.save(foundedGroup);

    }
    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable Long id){
        scheduleRepository.deleteAll(scheduleRepository.findByGroupId(id));
        groupRepository.deleteById(id);
        return "DELETED";
    }
    @PostMapping("/")
    public Group createGroup(@RequestBody Group group){
        if(groupRepository.isExist(group)) return groupRepository.find(group);
        return groupRepository.save(group);
    }}
