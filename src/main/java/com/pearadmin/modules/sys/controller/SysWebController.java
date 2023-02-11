package com.pearadmin.modules.sys.controller;


import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.modules.sys.domain.SysFile;
import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.domain.SysVisitData;
import com.pearadmin.modules.sys.mapper.SysWebMapper;
import com.pearadmin.modules.sys.service.SysFileService;
import com.pearadmin.modules.sys.service.SysWebService;
import com.pearadmin.modules.sys.service.impl.SysFileServiceImpl;
import io.swagger.annotations.Api;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"Web入口"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "/web/")
public class SysWebController extends BaseController {

    private static String MODULE_PATH = "system/web/";

    @Autowired
    SysWebService sysWebService;


    @Autowired()
    SysFileServiceImpl sysFileService;

    @GetMapping("info/{categoryId}")
    public ModelAndView info(Model model, @PathVariable("categoryId") String categoryId) {
        if(categoryId.equals("13432167553")){
            sysWebService.visitOne();
        } else if (categoryId.equals("16134523735")) {
            sysWebService.visitTwo();
        }
        SysType sysType = sysWebService.queryType(categoryId);
        model.addAttribute("categoryName", sysType.getTypeName());
        model.addAttribute("date", sysType.getDate());
        model.addAttribute("paragraphOne", sysType.getParagraphOne());
        model.addAttribute("paragraphTwo", sysType.getParagraphTwo());
        model.addAttribute("abstractOne", sysType.getAbstractOne());
        model.addAttribute("abstractTwo", sysType.getAbstractTwo());
        model.addAttribute("imageOne", "/system/file/download/" + sysType.getImageOne());
        model.addAttribute("imageTwo", "/system/file/download/" + sysType.getImageTwo());

        return jumpPage("/web/info");
    }

    @GetMapping("main/{categoryId}")
    public ModelAndView main(Model model, @PathVariable("categoryId") String categoryId) {
        List<SysFile> files = sysFileService.data();
        model.addAttribute("files", files);
        SysType sysType = sysWebService.queryType(categoryId);
        model.addAttribute("categoryName", sysType.getTypeName());
        model.addAttribute("typeId", sysType.getTypeId());
        model.addAttribute("date", sysType.getDate());
        model.addAttribute("paragraphOne", sysType.getParagraphOne());
        model.addAttribute("paragraphTwo", sysType.getParagraphTwo());
        model.addAttribute("abstractOne", sysType.getAbstractOne());
        model.addAttribute("abstractTwo", sysType.getAbstractTwo());

        System.err.println(sysType.getImageOne());

        model.addAttribute("imageOne", sysType.getImageOne());
        model.addAttribute("imageTwo", sysType.getImageTwo());
        return jumpPage(MODULE_PATH + "main");
    }

    @ResponseBody
    @RequestMapping(value = "main/update/",method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('/system/web/main/update/','system:web:main:update')")
    public Result mainUpdate(@RequestBody SysType sysType) {
        return Result.decide(sysWebService.updateContent(sysType));
    }

    @ResponseBody
    @RequestMapping(value = "console/")
    @PreAuthorize("hasPermission('/system/web/console','system:web:console')")
    public SysVisitData console() {
        return sysWebService.queryCurrentVisitDate();
    }





    /**
     * 页面跳转
     */
    public ModelAndView jumpPage(String path) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        return modelAndView;
    }

    /**
     * 带参数的页面跳转
     */
    public ModelAndView jumpPage(String path, Map<String, ?> params) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(path);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }
}
