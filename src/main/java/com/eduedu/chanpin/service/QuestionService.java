package com.eduedu.chanpin.service;

import com.eduedu.chanpin.dao.QuestionDao;
import com.eduedu.chanpin.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public int addQuestion(Question question){
        return questionDao.addQuestion(question);
    }

    public List findQuestionBySubjectId(Long subjectId){

        return questionDao.findQuestionBySubjectId(subjectId);
    }

    public Question findQuestionById(Long id){
        return questionDao.findQuestionById(id);
    }

    public void editQuestion(Question question){
        questionDao.editQuestion(question);
    }
}
