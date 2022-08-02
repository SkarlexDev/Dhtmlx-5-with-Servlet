package com.dao;

import com.bean.College;

import java.util.List;
import java.util.Optional;

public interface CollegeDao {

    Optional<College> findByName(String name);

    boolean save(College e);

    List<College> findAll();

    List<String> findAllName();

}
