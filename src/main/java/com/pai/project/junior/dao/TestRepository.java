package com.pai.project.junior.dao;

import com.pai.project.junior.entity.Test;
import com.pai.project.junior.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test,Integer> {

}
