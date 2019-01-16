package com.eduedu.chanpin.dao;

import com.eduedu.chanpin.domain.Subject;

import java.util.List;

public interface SubjectDao {

    /**
     * 获取所有科目列表
     *
     * @return
     */
    List<Subject> allSubject();

    /**
     * 根据ID查询科目
     *
     * @param id
     * @return
     */
    Subject findSubjectById(Long id);

    /**
     * 插入科目
     *
     * @param subject
     * @return
     */
    Long addSubject(Subject subject);

    /**
     * 修改科目
     * @param subject
     * @return
     */
    void editSubject(Subject subject);
}
