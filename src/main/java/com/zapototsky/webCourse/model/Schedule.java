package com.zapototsky.webCourse.model;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "schedule")
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time")
    private String time;

    @Column(name = "classroom")
    private String classroom;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "disciplines_id")
    private Discipline discipline;


    public Schedule(String time, String classroom, Group group, Teacher teacher, Discipline discipline) {
        this.time = time;
        this.classroom = classroom;
        this.group = group;
        this.teacher = teacher;
        this.discipline = discipline;
    }

    public Schedule() {

    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", classroom='" + classroom + '\'' +
                ", group=" + group +
                ", teacher=" + teacher +
                ", discipline=" + discipline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        Schedule schedule = (Schedule) o;
        return time.equals(schedule.time) && classroom.equals(schedule.classroom) && group.equals(schedule.group) && teacher.equals(schedule.teacher) && discipline.equals(schedule.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, classroom, group, teacher, discipline);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
