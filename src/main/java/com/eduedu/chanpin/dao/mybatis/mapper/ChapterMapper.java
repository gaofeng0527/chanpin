package com.eduedu.chanpin.dao.mybatis.mapper;

import com.eduedu.chanpin.domain.Chapter;

import java.util.List;

public interface ChapterMapper {

    /**
     * 查询某个科目下的所有章节
     * @param subjectId
     * @return
     */
    List<Chapter> findChapterBySubjectId(Long subjectId);

    /**
     * 添加某个章节
     * @param chapter
     * @return
     */
    int addChapter(Chapter chapter);

    /**
     * 修改章节
     * @param chapter
     */
    void editChapter(Chapter chapter);

    /**
     * 根据Id查询章节
     * @param id
     * @return
     */
    Chapter findChapterById(Long id);

    /**
     * 生成xml用的查询方法
     * @param subjectId
     * @return
     */
    List<Chapter> chapters(Long subjectId);


}
