package com.eduedu.chanpin.dao;

import com.eduedu.chanpin.domain.Chapter;

import java.util.List;

public interface ChapterDao {

    /**
     * 某个科目下的所有章节
     * @param subjectId
     * @return
     */
    List<Chapter> findChapterBySubjectId(Long subjectId);

    /**
     * 添加章节
     * @param chapter
     * @return
     */
    int addChapter(Chapter chapter);

    /**
     * 修改章节信息
     * @param chapter
     */
    void editChapter(Chapter chapter);

    /**
     * 根据Id查询
     * @param chapterId
     * @return
     */
    Chapter findChapterById(Long chapterId);

    /**
     * 生成xml用的查询方法
     * @param subjectId
     * @return
     */
    List<Chapter> chapters(Long subjectId);
}
