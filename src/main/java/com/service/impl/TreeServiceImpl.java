package com.service.impl;

import com.bean.College;
import com.dao.CollegeDao;
import com.dao.impl.CollegeDaoImpl;
import com.service.TreeService;

import java.util.List;
import java.util.logging.Logger;

public class TreeServiceImpl implements TreeService {

    private static final Logger log = Logger.getLogger(TreeServiceImpl.class.getName());

    private final CollegeDao facultyDao = new CollegeDaoImpl();

    @Override
    public StringBuilder createXmlTree() {
        log.info("Request to generate tree xml");
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\"?>");
        xml.append("<tree id='0'>");
        for (College dao : getAllCollege()) {
            xml.append("<item id='").append(itemID(dao)).append("' text='").append(dao.getCollegeName()).append("' open='1'>");
            for (int i = 1; i <= dao.getYears(); i++) {
                xml.append("<item id='").append(itemID(i, dao)).append("' text='Year ").append(i).append("' im0='folderClosed.gif'></item>");
            }
            xml.append("</item>");
        }
        xml.append("</tree>");
        return xml;
    }

    @Override
    public List<College> getAllCollege() {
        return facultyDao.findAll();
    }

    public String itemID(int i, College dao) {
        return i + dao.getCollegeName().replaceAll(" ", "");
    }

    public String itemID(College dao) {
        log.info("Generating parent id for xml");
        return dao.getCollegeName().replaceAll(" ", "");
    }
}
