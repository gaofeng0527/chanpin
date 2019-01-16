package com.eduedu.chanpin.controller;

import com.eduedu.chanpin.domain.Know;
import com.eduedu.chanpin.domain.Question;
import com.eduedu.chanpin.domain.Subject;
import com.eduedu.chanpin.domain.dto.Result;
import com.eduedu.chanpin.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SubjectController {

    public static final Logger logger = LoggerFactory.getLogger(SubjectController.class.getName());

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private KnowService knowService;


    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/subjects", method = RequestMethod.GET)
    public String subjects() {
        return "/page/subject/subjectList.html";
    }

    /**
     * 获取所有科目列表
     *
     * @return
     */
    @RequestMapping(value = "/admin/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public Result subjectList() {
        List<Subject> list = subjectService.allSubjects();
        Result result = new Result(true, "", list);
        return result;
    }


    /**
     * 科目总体操作页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/addSubject", method = RequestMethod.GET)
    public String addSubject() {
        return "page/subject/addSubject.html";
    }

    @RequestMapping(value = "/admin/addSubject-q", method = RequestMethod.GET)
    public String addSubjectQuestion() {
        return "page/questions/addQuestions.html";
    }

    /**
     * 编辑科目
     *
     * @param subjectId
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/subject/edit/{subjectId}", method = RequestMethod.GET)
    public String editSubject(@PathVariable Long subjectId, Model model) {
        Subject subject = subjectService.findSubjectById(subjectId);
        model.addAttribute("subject", subject);
        return "page/subject/editSubject.html";
    }

    @RequestMapping(value = "/admin/subject/edit-q/{subjectId}", method = RequestMethod.GET)
    public String editSubjectQuestions(@PathVariable Long subjectId, Model model) {
        Subject subject = subjectService.findSubjectById(subjectId);
        model.addAttribute("subject", subject);
        return "page/questions/editQuestions.html";
    }

    @RequestMapping(value = "/admin/json", method = RequestMethod.GET)
    @ResponseBody
    public Result getJson() {
        return new Result(true, "zhegncheng", null);
    }


    /**
     * 添加科目
     *
     * @param subject
     * @return
     */
    @PostMapping(value = "/admin/subject/add")
    @ResponseBody
    public Result subjectAdd(@RequestBody Subject subject) {
        Result result = new Result(true, "success");
        try {
            subject.setAddTime(new Date());
            subjectService.addSubject(subject);
            result.setData(subject.getId());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("添加科目错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @PostMapping(value = "/admin/subject/edit")
    @ResponseBody
    public Result subjectEdit(@RequestBody Subject subject) {
        Result result = new Result(true, "success");
        try {
            subjectService.editSubject(subject);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("修改科目错误，错误原因：" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/admin/subject/down/video/{subjectId}")
    public String downVideoConfig(@PathVariable Long subjectId, HttpServletRequest request, HttpServletResponse response) {
        String fileName = "video_course_cfg_g.xml";
        String path = request.getSession().getServletContext().getRealPath("");
        File file = createXML(subjectId, fileName,path);
        if (null != file && file.exists()) {
            down(response, fileName, file);
            return null;
        } else {
            return "/page/404.html";
        }

    }


    @GetMapping("/admin/subject/down/course/{subjectId}")
    public String downCourseConfig(@PathVariable Long subjectId, HttpServletRequest request, HttpServletResponse response) {
        String fileName = "course_config_g.xml";
        String path = request.getSession().getServletContext().getRealPath("");
        File file = createXML(subjectId, fileName,path);
        if (null != file && file.exists()) {
            down(response, fileName, file);
            return null;
        } else {
            return "/page/404.html";
        }

    }

    public void down(HttpServletResponse response, String fileName, File file) {
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + fileName);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }


    public File createXML(Long subjectId, String fileName,String path) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询科目信息
        Subject subject = subjectService.findSubjectById(subjectId);
        map.put("subject", subject);

        List<Know> knows = knowService.findKnowBySubjectId(subjectId);
        map.put("knows",knows);
        //查询测评题
        List<Question> qlist = questionService.findQuestionBySubjectId(subjectId);
        map.put("questions", qlist);

        File newFile = new File(path + File.separator + subjectId + File.separator + fileName);


        try {
            this.createFile(newFile);
            OutputStream out = new FileOutputStream(newFile);
            InputStream in = this.getClass().getResourceAsStream("/velocity_templates/" + fileName);
            if (templateService.render(in, out, map)) {
                out.close();
            } else {
                throw new IOException("生成文件出错");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile;
    }

    public void createFile(File file) throws IOException {
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdir();
        }
        file.createNewFile();
    }


}
