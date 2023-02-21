package com.pearadmin.modules.sys.controller;

import com.github.pagehelper.PageInfo;

import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.modules.sys.domain.SysOrder;
import com.pearadmin.modules.sys.domain.SysOrderProgress;
import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.service.SysTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PreAuthorize("hasPermission('/system/order/date','sys:order:date')")
    public ResultTable getAllOrderData(PageDomain pageDomain, SysType param){
        PageInfo<SysType> pageInfo=sysTypeService.queryAllTypes(param,pageDomain);
        return pageTable(pageInfo.getList(),pageInfo.getTotal());
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
