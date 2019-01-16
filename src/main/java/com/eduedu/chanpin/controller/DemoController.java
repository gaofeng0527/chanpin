package com.eduedu.chanpin.controller;

import com.eduedu.chanpin.domain.Subject;
import com.eduedu.chanpin.domain.dto.Result;
import com.eduedu.chanpin.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * demo学习
 */
@Controller
public class DemoController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String getIndex() {
        return "Welcome to Chanpinbu!";
    }

    @RequestMapping(value = "/errors", method = RequestMethod.GET)
    public String errors() {
        return "/page/404.html";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "/page/success.html";
    }

    @PostMapping("/hello")
    public String postIndex(Model model) {
        model.addAttribute("name", "Long");
        return "hello.htm";
    }

    @RequestMapping(value = "/dome/subjectadd", method = RequestMethod.GET)
    @ResponseBody
    public Result subjectDome() {
        Subject s = new Subject();
        s.setTitle("自我修养2");
        s.setModule("2");
        s.setCode("002");
        s.setRequireRTE(true);
        s.setRequireAQ(true);
        s.setDefinition("3");
        s.setAddTime(new Date());
        Long subjectId = subjectService.addSubject(s);
        System.out.println(subjectId);
        return new Result(true, "success", s.getId());

    }

}
