package com.eduedu.chanpin.dao.mybatis;

import com.eduedu.chanpin.dao.QuestionDao;
import com.eduedu.chanpin.dao.mybatis.mapper.QuestionMapper;
import com.eduedu.chanpin.domain.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public int addQuestion(Question question) {
        return questionMapper.addQuestion(question);
    }

    @Override
    public List<Question> findQuestionBySubjectId(Long subjectId) {
        return questionMapper.findQuestionBySubjectId(subjectId);
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionMapper.findQuestionById(id);
    }

    @Override
    public void editQuestion(Question question) {
        questionMapper.editQuestion(question);
    }

    @Override
    public Question findById(Long id) {
        return null;
    }

}
