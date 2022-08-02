package com.service;

import com.bean.College;

import java.util.Optional;

public interface CollegeService {

    boolean create(College e);

    Optional<College> findByName(String name);

}
