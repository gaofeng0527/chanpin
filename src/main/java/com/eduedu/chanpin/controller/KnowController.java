package com.eduedu.chanpin.controller;

import com.eduedu.chanpin.domain.Know;
import com.eduedu.chanpin.domain.dto.Result;
import com.eduedu.chanpin.service.KnowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class KnowController {

    @Autowired
    private KnowService knowService;

    /**
     * 根据科目ID查询知识点
     *
     * @param subjectId
     * @return
     */
    @RequestMapping(value = "/admin/know/list", method = RequestMethod.GET)
    @ResponseBody
    public Result knowList(@RequestParam Long subjectId) {
        List<Know> klist = new ArrayList<Know>();
        Result result = new Result(true, "success");
        try {
            klist = knowService.findKnowBySubjectId(subjectId);
            result.setData(klist);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("获取知识点错误,错误原因：" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/admin/know/edit/{id}")
    @ResponseBody
    public Result know(@PathVariable Long id) {
        Result result = new Result(true, "success");
        try {
            Know know = knowService.findKnowById(id);
            result.setData(know);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("根据ID：" + id + "查询知识点错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @PostMapping("/admin/know/edit")
    @ResponseBody
    public Result editKnow(@RequestBody Know know) {
        Result result = new Result(true, "success");
        try {
            knowService.editKnow(know);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("修改知识点错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @PostMapping("/admin/know/add")
    @ResponseBody
    public Result addKnow(@RequestBody Know know) {

        Result result = new Result(true, "success");
        System.out.println(know.toString());
        try {
            knowService.addKnow(know);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("添加知识点错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/admin/know/getCode/{subjectId}")
    @ResponseBody
    public Result createKnowCode(@PathVariable Long subjectId) {
        Result result = new Result(true, "success");
        List<Know> klist = new ArrayList<>();
        klist = knowService.findKnowBySubjectId(subjectId);
        int count = klist.size() + 1;
        String code = String.format("%02d", count);
        result.setData("cha_" + code + "_know");
        return result;
    }
}
