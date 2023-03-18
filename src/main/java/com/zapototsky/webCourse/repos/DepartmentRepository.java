package com.zapototsky.webCourse.repos;

import com.zapototsky.webCourse.model.Department;
import com.zapototsky.webCourse.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    default Department find(Department department){
        return this.findAll().stream().filter(x -> x.equals(department)).findFirst().orElse(null);
    }
    default boolean isExist(Department department){
        return this.findAll().stream().anyMatch(x -> x.equals(department));
    }
}
