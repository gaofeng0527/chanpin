package com.eduedu.chanpin.dao.mybatis;


import com.eduedu.chanpin.dao.SubjectDao;
import com.eduedu.chanpin.dao.mybatis.mapper.SubjectMapper;
import com.eduedu.chanpin.domain.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    private static final Logger logger = LoggerFactory.getLogger(SubjectDaoImpl.class.getName());

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> allSubject() {
        return subjectMapper.allSubject();
    }

    @Override
    public Subject findSubjectById(Long id) {
        return subjectMapper.findSubjectById(id);
    }

    @Override
    public Long addSubject(Subject subject) {
        return subjectMapper.addSubject(subject);
    }

    @Override
    public void editSubject(Subject subject) {
        subjectMapper.editSubject(subject);
    }


}
