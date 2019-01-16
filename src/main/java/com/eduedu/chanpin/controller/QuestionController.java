package com.eduedu.chanpin.controller;

import com.eduedu.chanpin.domain.Question;
import com.eduedu.chanpin.domain.dto.Result;
import com.eduedu.chanpin.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/admin/question-subject")
    public String qSubject() {
        return "/page/questions/questionsList.html";
    }

    /**
     * 知识点下的所有测评题列表
     *
     * @param subjectId
     * @return
     */
    @RequestMapping(value = "/admin/question/list", method = RequestMethod.GET)
    @ResponseBody
    public Result questionList(@RequestParam Long subjectId) {
        Result result = new Result(true, "success");
        List<Question> qlist = new ArrayList<>();
        try {
            qlist = questionService.findQuestionBySubjectId(subjectId);
            result.setData(qlist);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("根据科目Id（" + subjectId + "），查询测评题错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @PostMapping("/admin/question/add")
    @ResponseBody
    public Result addQuestion(@RequestBody Question question) {
        Result result = new Result(true, "success");
        try {
            if(null == question.getAnswer() || "".equals(question.getAnswer())){
                result.setSuccess(false);
                result.setMessage("添加测评题错误，请输入正确答案");
            }else{
                question.setAnswer(question.getAnswer().toUpperCase());
            }
            if("single_choice".equals(question.getType())){
                question.setTitle("（单选题）"+question.getTitle());
            }else{
                question.setTitle("（多选题）"+question.getTitle());
            }
            questionService.addQuestion(question);

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("添加测评题错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/admin/question/edit/{id}")
    @ResponseBody
    public Result question(@PathVariable Long id) {
        Result result = new Result(true, "success");
        Question question = new Question();
        try {
            question = questionService.findQuestionById(id);
            result.setData(question);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("根据题目Id（" + id + "）获取题目错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @PostMapping("/admin/question/edit")
    @ResponseBody
    public Result editQuestion(@RequestBody Question question) {
        Result result = new Result(true, "success");
        try {
            questionService.editQuestion(question);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("修改题目错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/admin/question/getCode/{subjectId}")
    @ResponseBody
    public Result getQuestionCode(@PathVariable Long subjectId) {
        Result result = new Result(true, "success");
        List<Question> qlist = new ArrayList<>();
        try {
            qlist = questionService.findQuestionBySubjectId(subjectId);
            int count = qlist.size()+1;
            String code = String.format("%03d",count);
            result.setData("ques_2000"+code);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("根据科目Id（" + subjectId + "），查询测评题错误，错误原因：" + e.getMessage());
        }
        return result;
    }
}
