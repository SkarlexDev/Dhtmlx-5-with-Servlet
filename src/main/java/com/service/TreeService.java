package com.service;

import java.util.List;

import com.bean.College;

public interface TreeService {

    public StringBuilder createXmlTree();

    public List<College> getAllCollege();
}
