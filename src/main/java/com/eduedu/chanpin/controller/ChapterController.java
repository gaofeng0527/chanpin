package com.eduedu.chanpin.controller;

import com.eduedu.chanpin.domain.Chapter;
import com.eduedu.chanpin.domain.dto.Result;
import com.eduedu.chanpin.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    /**
     * 根据科目id查询所有章节
     *
     * @param subjectId
     * @return
     */
    @RequestMapping(value = "/admin/chapter/list", method = RequestMethod.GET)
    @ResponseBody
    public Result chapterList(Long subjectId) {
        Result result = new Result(true,"success");
        List<Chapter> clist = new ArrayList<>();
        try {
            clist = chapterService.findChapterBySubjectId(subjectId);
            result.setData(clist);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("根据科目ID（"+subjectId+"）获取章节列表错误，错误原因："+e.getMessage());
        }
        return result;
    }

    @PostMapping("/admin/chapter/add")
    @ResponseBody
    public Result addChapter(@RequestBody Chapter chapter){
        Result result = new Result(true,"success");
        try {
            chapterService.addChapter(chapter);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加章节错误，错误原因："+e.getMessage());
        }
        return result;

    }


    @GetMapping("/admin/chapter/edit/{chapterId}")
    @ResponseBody
    public Result chapter(@PathVariable Long chapterId){
        Result result = new Result(true,"success");
        try {
            Chapter chapter = chapterService.findChapterById(chapterId);
            result.setData(chapter);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("查询章节失败，失败原因："+e.getMessage());
        }
        return result;
    }

    @PostMapping("/admin/chapter/edit")
    @ResponseBody
    public Result editChapter(@RequestBody Chapter chapter){
        Result result = new Result(true,"success");
        try {
            chapterService.editChapter(chapter);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("添加章节错误，错误原因："+e.getMessage());
        }
        return result;

    }
}
