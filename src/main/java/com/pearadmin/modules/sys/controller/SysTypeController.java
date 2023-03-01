package com.pearadmin.modules.sys.controller;

import com.github.pagehelper.PageInfo;

import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.modules.sys.domain.SysFile;

import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.service.SysFileService;
import com.pearadmin.modules.sys.service.SysTypeService;
import com.pearadmin.modules.sys.service.impl.SysFileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@RestController
@Api(tags = {"Web入口"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "type")
public class SysTypeController extends BaseController {

    private static String MODULE_PATH = "system/type/";
    @Autowired
    SysTypeService sysTypeService;

    @Autowired
    SysFileServiceImpl sysFileService;

    //    类型列表
    @GetMapping("main")
    @ApiOperation(value = "类型列表")
    @PreAuthorize("hasPermission('/system/type/main','sys:type:main')")
    public ModelAndView getOrderProgress() {

        return jumpPage(MODULE_PATH+"main");
    }

    //    所有订单数据
    @RequestMapping("/data")
    @ApiOperation(value = "获取类型数据")
    @PreAuthorize("hasPermission('/system/type/date','sys:type:date')")
    public ResultTable getAllOrderData(PageDomain pageDomain, SysType param){
        PageInfo<SysType> pageInfo=sysTypeService.queryAllTypes(param,pageDomain);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
    }

    //    所有订单数据
    @RequestMapping("/getAddPage")
    @ApiOperation(value = "获取添加类型页面")
    @PreAuthorize("hasPermission('/system/type/add','sys:type:add')")
    public ModelAndView getAddTypePage(Model model){
        List<SysFile> data = sysFileService.data();
        model.addAttribute("files",data);
        return jumpPage(MODULE_PATH+"add");
    }

    //    所有订单数据
    @PutMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加类型")
    @PreAuthorize("hasPermission('/system/type/add','sys:type:add')")
    public Result addType(@RequestBody SysType sysType){
        return Result.decide(sysTypeService.addType(sysType));
    }

    //    所有订单数据
    @PutMapping("/typeIsShow/{operate}")
    @ResponseBody
    @ApiOperation(value = "修改isShow")
    @PreAuthorize("hasPermission('/system/type/edit','sys:type:edit')")
    public Result typeIsShow(@RequestBody SysType sysType,@PathVariable("operate")String operate){
        System.err.println(operate);
        sysType.setIsShow(operate);
        return Result.decide(sysTypeService.updateIsShow(sysType));
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
