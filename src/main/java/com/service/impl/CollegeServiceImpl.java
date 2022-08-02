package com.service.impl;

import com.bean.College;
import com.dao.CollegeDao;
import com.dao.impl.CollegeDaoImpl;
import com.service.CollegeService;

import java.util.Optional;
import java.util.logging.Logger;

public class CollegeServiceImpl implements CollegeService {

    private static final Logger log = Logger.getLogger(CollegeServiceImpl.class.getName());

    private final CollegeDao collegeDao = new CollegeDaoImpl();

    @Override
    public boolean create(College e) {
        log.info("Request to save College");
        return collegeDao.save(e);
    }

    @Override
    public Optional<College> findByName(String name) {
        log.info("Request to find College by name");
        return collegeDao.findByName(name.replaceAll("\\d", ""));
    }
}
