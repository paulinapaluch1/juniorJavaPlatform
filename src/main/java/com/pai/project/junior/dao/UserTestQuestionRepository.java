package com.pai.project.junior.dao;

import com.pai.project.junior.entity.UserTest;
import com.pai.project.junior.entity.UserTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTestQuestionRepository extends JpaRepository<UserTestQuestion,Integer> {


    List<UserTestQuestion> findByUserTest(UserTest test);
}
