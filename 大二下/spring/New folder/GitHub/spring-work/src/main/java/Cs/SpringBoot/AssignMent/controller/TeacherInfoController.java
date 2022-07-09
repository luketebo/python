package Cs.SpringBoot.AssignMent.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Cs.SpringBoot.AssignMent.entity.TeacherInfo;
import Cs.SpringBoot.AssignMent.mapper.TeacherInfoMapper;
import Cs.SpringBoot.AssignMent.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import Cs.SpringBoot.AssignMent.service.ClassInfoService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 老师表 前端控制器
 * </p>
 *
 * @author Dog
 * @since 2022-06-12 09:06:33
 */
@Controller
@RequestMapping("/AssignMent/teacher")
public class TeacherInfoController {

    @Autowired
    private TeacherInfoService teacherInfoService;

    @Value("${img.file.dir}")
    private String realpath;

    @Autowired
    private ClassInfoService classInfoService;

    @GetMapping("admin")
    public String teacher(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        Page<TeacherInfo> page = new Page<>(pn,14);
        teacherInfoService.page(page, null);
        boolean next = page.hasNext();//是否有下一页
        model.addAttribute("next",next);
        boolean pre = page.hasPrevious();//是否有上一页
        model.addAttribute("previous",pre);
        List<TeacherInfo> l = page.getRecords();//获得的记录
        model.addAttribute("teachers",l);
        long now = page.getCurrent();//现在在哪一页
        model.addAttribute("now", now);
//        long last = page.getPages();//一共有多少页
//        model.addAttribute("last", last);
        return "admin/admin_teacher";
    }

    //图像上传
    private String uploadPhoto(MultipartFile img, String originalFilename) throws IOException {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = fileName + ext;
        img.transferTo(new File(realpath, newFilename));
        return newFilename;
    }

    @PostMapping("/save")
    public String save(TeacherInfo teacherInfo,MultipartFile img) throws IOException{
        String originalFilename = img.getOriginalFilename();
        String newFilename = uploadPhoto(img, originalFilename);
        teacherInfo.setPhoto(newFilename);
        teacherInfoService.save(teacherInfo);
        return "redirect:/AssignMent/teacher/admin";
    }

    @PostMapping("/update")
    public String updatet(TeacherInfo teacherInfo,MultipartFile img) throws IOException{
        String originalFilename = img.getOriginalFilename();
        String newFilename = uploadPhoto(img, originalFilename);
        teacherInfo.setPhoto(newFilename);
        teacherInfoService.updateById(teacherInfo);
        return "redirect:/AssignMent/teacher/admin";
    }

    @GetMapping("deltea")
    public String deltea(Integer id){
        teacherInfoService.removeById(id);
        classInfoService.Delete(id);
        return "redirect:/AssignMent/teacher/admin";
    }

}
