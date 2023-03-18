package com.zapototsky.webCourse.controller;

import com.zapototsky.webCourse.model.Department;
import com.zapototsky.webCourse.repos.DepartmentRepository;
import com.zapototsky.webCourse.repos.FacultyRepository;
import com.zapototsky.webCourse.repos.GroupRepository;
import com.zapototsky.webCourse.repos.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
    @RequestMapping("/department")
    public class DepartmentController {
        @Autowired
        DepartmentRepository departmentRepository;
        @Autowired
        FacultyRepository facultyRepository;
        @Autowired
        GroupRepository groupRepository;
        @Autowired
        ScheduleRepository scheduleRepository;


        @GetMapping("/{id}")
        public Department getDepartment(@PathVariable Long id){

            return departmentRepository.findById(id).get();
        }

        @GetMapping()
        public List<Department> getAllDepartments(){
            return departmentRepository.findAll();
    }

        @PutMapping("/{id}")
        public Department updateDepartment(@PathVariable Long id,@RequestBody Department department){
            Department foundedDepartment = departmentRepository.findById(id).get();
            foundedDepartment.setName(department.getName());
            foundedDepartment.setShortName(department.getShortName());
            foundedDepartment.setFacultyId(department.getFacultyId());
            System.out.println(department);
            return departmentRepository.save(foundedDepartment);

        }
        @DeleteMapping("/{id}")
       public String deleteDepartment(@PathVariable Long id){
            departmentRepository.findById(id).get().getGroupList().forEach( x -> {
                scheduleRepository.deleteAll(scheduleRepository.findByGroupId(x.getId()));
            });
            departmentRepository.delete(departmentRepository.findById(id).get());
            return "DELETED";
        }
        @PostMapping("/")
        public Department createDepartment(@RequestBody Department department){
            if(departmentRepository.isExist(department)){return null;}
        return departmentRepository.save(department);
        }
}
