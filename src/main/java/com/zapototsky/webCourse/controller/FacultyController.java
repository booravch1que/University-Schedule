package com.zapototsky.webCourse.controller;


import com.zapototsky.webCourse.model.Faculty;
import com.zapototsky.webCourse.repos.DepartmentRepository;
import com.zapototsky.webCourse.repos.FacultyRepository;
import com.zapototsky.webCourse.repos.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;


    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable Long id){
        return facultyRepository.findById(id).get();
    }
    @GetMapping()
    public List<Faculty> getAllFaculties(){
        return facultyRepository.findAll();
    }

    @PutMapping("/{id}")
    public Faculty updateFaculty(@PathVariable Long id, @RequestBody Faculty faculty){
        System.out.println(faculty);
        Faculty foundedFaculty = facultyRepository.findById(id).get();
        foundedFaculty.setName(faculty.getName() == null ?
                foundedFaculty.getName() : faculty.getName());
        foundedFaculty.setDepartmentList(faculty.getDepartmentList() == null ?
                foundedFaculty.getDepartmentList() : faculty.getDepartmentList());
        foundedFaculty.setShortName(faculty.getShortName() == null ?
                foundedFaculty.getShortName() : faculty.getShortName());
        return facultyRepository.save(foundedFaculty);

    }
    @DeleteMapping("/{id}")
    public void deleteFaculty(@PathVariable Long id){
       facultyRepository.findAll().forEach(faculty -> {
           faculty.getDepartmentList().forEach(department -> {
               department.getGroupList().forEach(group -> {
                   scheduleRepository.deleteAll(scheduleRepository.findByGroupId(group.getId()));
               });
           });
       });
        facultyRepository.delete(facultyRepository.findById(id).get());
    }
    @PostMapping("")
    public Faculty createFaculty(@RequestBody Faculty faculty){
        if(facultyRepository.isExist(faculty)) return facultyRepository.find(faculty);
        return facultyRepository.save(faculty);
    }
}
