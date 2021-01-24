package com.pai.project.junior.dao;

import com.pai.project.junior.entity.Student;
import com.pai.project.junior.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest,Integer> {

    List<UserTest> findByDeleted(boolean deleted);

    List<UserTest> findByDeletedAndStudent(boolean deleted, Student student);
}
