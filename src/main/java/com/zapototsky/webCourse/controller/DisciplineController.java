package com.zapototsky.webCourse.controller;

import com.zapototsky.webCourse.model.Discipline;
import com.zapototsky.webCourse.repos.DisciplineRepository;
import com.zapototsky.webCourse.repos.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/discipline")
public class DisciplineController {
@Autowired
DisciplineRepository disciplineRepository;
@Autowired
ScheduleRepository scheduleRepository;

    @GetMapping("/{id}")
    public Discipline getDiscipline(@PathVariable Long id){
        return disciplineRepository.findById(id).get();
    }

    @GetMapping()
    public List<Discipline> getAllDisciplines(){
        return disciplineRepository.findAll();
    }

    @PutMapping("/{id}")
    public Discipline updateDiscipline(@PathVariable Long id,@RequestBody Discipline discipline){
        Discipline foundedDiscipline = disciplineRepository.findById(id).get();
        foundedDiscipline.setName(discipline.getName());
        return disciplineRepository.save(foundedDiscipline);

    }
    @DeleteMapping("/{id}")
    public String deleteDiscipline(@PathVariable Long id){
        scheduleRepository.deleteAll(scheduleRepository.findByDisciplineId(id));
        disciplineRepository.deleteById(id);
        return "DELETED";
    }
    @PostMapping("/")
    public Discipline createDiscipline(@RequestBody Discipline discipline){
        if(disciplineRepository.isExist(discipline)) return disciplineRepository.find(discipline);
       return disciplineRepository.save(discipline);
    }

}
