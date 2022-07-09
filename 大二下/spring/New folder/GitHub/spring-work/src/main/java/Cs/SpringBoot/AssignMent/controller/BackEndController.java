package Cs.SpringBoot.AssignMent.controller;

import java.util.Date;
import java.util.List;

import Cs.SpringBoot.AssignMent.entity.TeacherInfo;
import Cs.SpringBoot.AssignMent.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import Cs.SpringBoot.AssignMent.entity.Account;
import Cs.SpringBoot.AssignMent.entity.Assignment;
import Cs.SpringBoot.AssignMent.entity.Result;
import Cs.SpringBoot.AssignMent.service.AccountService;
import Cs.SpringBoot.AssignMent.service.AssignmentService;
import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
public class BackEndController {
    /*
     * 教师页面相关的后端接口
     */
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TeacherInfoService teacherInfoService;

    @Data
    @AllArgsConstructor
    private class homework<T> {
        private long pages;
        private long now;
        private List<T> records;
    }

    /* 获取自己的信息 */
    @GetMapping("/InfoGet/me")
    public TeacherInfo currentUserName(Authentication authentication) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", authentication.getName());
        Account teacher = accountService.getOne(queryWrapper);
        QueryWrapper<TeacherInfo> queryWrapper1 = new QueryWrapper<TeacherInfo>();
        queryWrapper1.like("name", teacher.getUsername());
        TeacherInfo teacherInfo = teacherInfoService.getOne(queryWrapper1);
        return teacherInfo;
    }
    
    /* 作业查询 */
    @GetMapping("/InfoGet/getAs")
    public Result<homework<Assignment>> getAs(@RequestParam int pages, @RequestParam int id) {
        QueryWrapper<Assignment> queryWrapper = new QueryWrapper<>();
        Page<Assignment> page = new Page<>(pages, 10);
        queryWrapper.eq("create_id", id);
        assignmentService.page(page, queryWrapper);
        return new Result<homework<Assignment>>(200, "assignment", new homework<>(page.getPages(), page.getCurrent(), page.getRecords()));
    }

    @PostMapping("/InfoPost/createAs")
    public Result<Boolean> createAs(@RequestBody Assignment assignment) {
        assignment.setDeadLine(new Date(assignment.getDeadLine().getTime()-(long)8*60*60*1000));
        return new Result<Boolean>(200, "createAs", assignmentService.save(assignment));
    }

    @PutMapping("InfoPut/updateAs")
    public Result<Boolean> updateAs(@RequestBody Assignment assignment) {
        QueryWrapper<Assignment> qw = new QueryWrapper<>();
        qw.like("title", assignment.getTitle());
        assignment.setDeadLine(new Date(assignment.getDeadLine().getTime()-(long)8*60*60*1000));
        return new Result<Boolean>(200, "updateAs", assignmentService.update(assignment, qw));
    }

    @DeleteMapping("/InfoDelete/deleteAs")
    public Result<Boolean> deleteAs(@RequestBody Assignment assignment) {
        QueryWrapper<Assignment> qw = new QueryWrapper<>();
        qw.like("title", assignment.getTitle());
        return new Result<Boolean>(200, "deleteAs", assignmentService.remove(qw));
    }
    // @PostMapping("/InfoPost/createAs")
    // @CrossOrigin
    // public void create() {
    //     System.out.println("fuck");
    // }

}
