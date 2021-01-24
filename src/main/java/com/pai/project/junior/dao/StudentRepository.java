package com.pai.project.junior.dao;

import com.pai.project.junior.entity.Student;
import com.pai.project.junior.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findById(int id);
}
