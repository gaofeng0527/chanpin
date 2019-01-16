package com.eduedu.chanpin.dao.mybatis.mapper;

import com.eduedu.chanpin.domain.Question;

import java.util.List;

public interface QuestionMapper {

    /**
     * 添加测评题
     * @param question
     * @return
     */
    int addQuestion(Question question);

    /**
     * 根据知识点查询对应测评题
     * @param subjectId
     * @return
     */
    List<Question> findQuestionBySubjectId(Long subjectId);

    /**
     * 查询单个问题
     * @param id
     * @return
     */
    Question findQuestionById(Long id);

    /**
     * 修改题目
     * @param question
     */
    void editQuestion(Question question);

    Question findById(Long id);
}
