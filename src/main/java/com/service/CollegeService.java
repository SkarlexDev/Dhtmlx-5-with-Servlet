package com.service;

import com.bean.College;

public interface CollegeService {

    public int create(College e);

    public College findByName(String name);

}
