package com.pai.project.junior.dao;

import com.pai.project.junior.entity.Project;
import com.pai.project.junior.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

    Student findById(int id);

    List<Project> findByDeletedAndStudent(boolean b, Student currentLoggedStudent);
}
