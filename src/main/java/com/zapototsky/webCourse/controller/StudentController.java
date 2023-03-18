package com.zapototsky.webCourse.controller;

import com.zapototsky.webCourse.model.Student;
import com.zapototsky.webCourse.repos.StudentRepository;
import com.zapototsky.webCourse.repos.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id){
        return studentRepository.findById(id).get();
    }

    @GetMapping(    )
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id,@RequestBody Student student){
        Student foundedStudent = studentRepository.findById(id).get();
        foundedStudent.setName(student.getName());
        foundedStudent.setEmail(student.getEmail());
        foundedStudent.setPhone(student.getPhone());
        return studentRepository.save(foundedStudent);

    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id){
        studentRepository.delete(studentRepository.findById(id).get());
        return "DELETED";
    }
    @PostMapping("/")
    public Student createStudent(@RequestBody Student student){
        System.out.println(student);
        if(studentRepository.isExist(student)){return studentRepository.find(student);}
        return studentRepository.save(student);
    }
    }