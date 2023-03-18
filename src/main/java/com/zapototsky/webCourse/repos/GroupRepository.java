package com.zapototsky.webCourse.repos;

import com.zapototsky.webCourse.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
    default Group find(Group group){
        return this.findAll().stream().filter(x -> x.equals(group)).findFirst().get();
    }
    default boolean isExist(Group group){
        return this.findAll().stream().anyMatch(x -> x.equals(group));
    }
}
