package com.eduedu.chanpin.dao.mybatis.mapper;

import com.eduedu.chanpin.domain.Know;

import java.util.List;

public interface KnowMapper {

    /**
     * 添加知识点
     * @param know
     * @return
     */
    int addKnow(Know know);

    /**
     * 修改知识点
     * @param know
     */
    void editKnow(Know know);

    /**
     * 根据章节ID查询对应的知识点
     * @param subjectId
     * @return
     */
    List<Know> findKnowBySubjectId(Long subjectId);

    /**
     * 查询单个知识点
     * @param id
     * @return
     */
    Know findKnowById(Long id);
}
