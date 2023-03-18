package com.zapototsky.webCourse.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id")
    private List<Department> departmentList;

    public Faculty(String name, String shortName,List<Department> departmentList) {
        this.name = name;
        this.shortName = shortName;
        this.departmentList = departmentList;
    }

    protected Faculty() {

    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", departmentList=" + departmentList +
                '}';
    }

    public List<Department> getDepartmentList() {return departmentList;}

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return name.equals(faculty.name) || shortName.equals(faculty.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortName);
    }
}
