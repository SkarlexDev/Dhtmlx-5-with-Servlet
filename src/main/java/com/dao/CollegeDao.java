package com.dao;

import java.util.List;

import com.bean.College;

public interface CollegeDao {

    public int save(College e);

    public List<College> findAll();

    public College findByName(String name);

    public List<String> findAllName();
}
