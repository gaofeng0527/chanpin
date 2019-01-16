package com.eduedu.chanpin.dao.mybatis;

import com.eduedu.chanpin.dao.ChapterDao;
import com.eduedu.chanpin.dao.mybatis.mapper.ChapterMapper;
import com.eduedu.chanpin.domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ChapterDaoImpl implements ChapterDao {

    @Autowired
    private ChapterMapper chapterMapper;

    @Override
    public List<Chapter> findChapterBySubjectId(Long subjectId) {
        return chapterMapper.findChapterBySubjectId(subjectId);
    }

    @Override
    public int addChapter(Chapter chapter) {
        return chapterMapper.addChapter(chapter);
    }

    @Override
    public void editChapter(Chapter chapter) {
        chapterMapper.editChapter(chapter);
    }

    @Override
    public Chapter findChapterById(Long chapterId) {
        return chapterMapper.findChapterById(chapterId);
    }

    @Override
    public List<Chapter> chapters(Long subjectId) {
        return chapterMapper.chapters(subjectId);
    }
}
