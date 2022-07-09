package Cs.SpringBoot.AssignMent.controller;

import java.util.List;

import Cs.SpringBoot.AssignMent.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import Cs.SpringBoot.AssignMent.entity.StudentInfo;

@Controller
public class FrontEndController {

    @Autowired
    private StudentInfoService studentInfoService;

    @GetMapping("/teacher")
    public String teacher(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        Page<StudentInfo> page = new Page<>(pn, 14);
        studentInfoService.page(page, null);
        boolean next = page.hasNext();
        model.addAttribute("next", next);
        boolean previous = page.hasPrevious();
        model.addAttribute("previous", previous);
        List<StudentInfo> records = page.getRecords();

        model.addAttribute("stu", records);
        long now = page.getCurrent();
        model.addAttribute("now", now);

        return "Teacher/teacher";
    }

    @GetMapping("/gaiNan")
    public String gaiNan(Model model)
    {
        List<StudentInfo> stu = studentInfoService.list();
        model.addAttribute("stu", stu);

        return "Teacher/teacherGaiNan";
    }

    @GetMapping("/chaKan")
    public String chaKan() {
        return "/Teacher/teacherChaKan";
    }
    @PostMapping("/update")
    public String updates(@RequestBody StudentInfo studentInfo) {
        studentInfoService.updateById(studentInfo);
        return "redirect:/teacher";
    }

    @PostMapping("/addstu")
    public String addstu(@RequestBody StudentInfo studentInfo) {
        System.out.println(studentInfo);
        System.out.println(studentInfo.getClass());
        studentInfoService.save(studentInfo);
//        return type(studentInfo);
        return "redirect:/teacher";
    }

    @GetMapping("/delstu")
    public String delstu(@RequestParam Integer id) {
        System.out.println(id);
        studentInfoService.removeById(id);
        System.out.println(studentInfoService.list());
        return "redirect:/teacher";
    }

    /*
     * 返回老师管理页面的相关内容
     */
    @GetMapping("/createAs")
    public String createAssignment() {
        return "/Teacher/createAs";
    }

    @GetMapping("/checkAs")
    public String checkAssignment() {
        return "/Teacher/checkAs";
    }

    @GetMapping("/readAs")
    public String readAssignment() {
        return "/Teacher/readAs";
    }

    @GetMapping("/createCl")
    public String createCl() {
        return "/Teacher/createCl";
    }
}
