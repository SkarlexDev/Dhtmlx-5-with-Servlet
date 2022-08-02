package com.service;

import com.bean.College;

import java.util.List;

public interface TreeService {

    StringBuilder createXmlTree();

    List<College> getAllCollege();
}
