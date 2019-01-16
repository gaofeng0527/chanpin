package com.eduedu.chanpin.service;

import com.eduedu.chanpin.dao.KnowDao;
import com.eduedu.chanpin.domain.Know;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowService {

    @Autowired
    private KnowDao knowDao;

    public int addKnow(Know know){
        return knowDao.addKnow(know);
    }

    public void editKnow(Know know){
        knowDao.editKnow(know);
    }

    public List<Know> findKnowBySubjectId(Long subjectId){
        return knowDao.findKnowBySubjectId(subjectId);
    }

    public Know findKnowById(Long id){
        return knowDao.findKnowById(id);
    }


}
