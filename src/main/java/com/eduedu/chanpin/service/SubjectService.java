package com.eduedu.chanpin.service;

import com.eduedu.chanpin.dao.SubjectDao;
import com.eduedu.chanpin.domain.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class.getName());

    @Autowired
    private SubjectDao subjectDao;

    public List<Subject> allSubjects() {

        return subjectDao.allSubject();
    }

    public Subject findSubjectById(Long id) {
        return subjectDao.findSubjectById(id);
    }

    public Long addSubject(Subject subject) {

        return subjectDao.addSubject(subject);
    }

    public void editSubject(Subject subject){
        subjectDao.editSubject(subject);
    }
}
