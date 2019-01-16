package com.eduedu.chanpin.service;

import com.eduedu.chanpin.dao.ChapterDao;
import com.eduedu.chanpin.domain.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterDao chapterDao;

    public List<Chapter> findChapterBySubjectId(Long subjectId){
        return chapterDao.findChapterBySubjectId(subjectId);
    }

    public int addChapter(Chapter chapter){
        return chapterDao.addChapter(chapter);
    }

    public void editChapter(Chapter chapter){
        chapterDao.editChapter(chapter);
    }

    public Chapter findChapterById(Long chapterId){
        return chapterDao.findChapterById(chapterId);
    }

    public List<Chapter> chapters(Long subjectId){
        return chapterDao.chapters(subjectId);
    }
}
