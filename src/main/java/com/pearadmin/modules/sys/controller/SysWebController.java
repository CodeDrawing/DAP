package com.pearadmin.modules.sys.controller;


import com.github.pagehelper.PageInfo;
import com.pearadmin.common.constant.ControllerConstant;
import com.pearadmin.common.context.UserContext;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.common.web.domain.response.Result;
import com.pearadmin.common.web.domain.response.module.ResultTable;
import com.pearadmin.modules.sys.domain.*;
import com.pearadmin.modules.sys.mapper.SysWebMapper;
import com.pearadmin.modules.sys.service.SysFileService;
import com.pearadmin.modules.sys.service.SysOrderService;
import com.pearadmin.modules.sys.service.SysUserService;
import com.pearadmin.modules.sys.service.SysWebService;
import com.pearadmin.modules.sys.service.impl.SysFileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.security.Security;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"Web入口"})
@RequestMapping(ControllerConstant.API_SYSTEM_PREFIX + "/web/")
public class SysWebController extends BaseController {

    private static String MODULE_PATH = "system/web/";

    @Autowired
    SysWebService sysWebService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysFileServiceImpl sysFileService;
    @Autowired
    SysOrderService sysOrderService;

    @GetMapping("info/{categoryId}")
    public ModelAndView info(Model model, @PathVariable("categoryId") String categoryId) {
        if (categoryId.equals("13432167553")) {
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


        model.addAttribute("imageOne", sysType.getImageOne());
        model.addAttribute("imageTwo", sysType.getImageTwo());
        return jumpPage(MODULE_PATH + "main");
    }

    @ResponseBody
    @RequestMapping(value = "main/update/", method = RequestMethod.PUT)
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

    @GetMapping("center")
    @ApiOperation(value = "修改个人信息视图")
    @PreAuthorize("hasPermission('/system/web/center','sys:web:center')")
    public ModelAndView center(Model model) {
        model.addAttribute("sysRoles", sysUserService.getUserRole(UserContext.currentUser().getUserId()));
        SysUser userId = sysUserService.getById(UserContext.currentUser().getUserId());
        model.addAttribute("sysUser", userId);
        return jumpPage(MODULE_PATH + "center");
    }

    @GetMapping("getOrder")
    @ApiOperation(value = "下单")
    @PreAuthorize("hasPermission('/system/web/order','sys:web:order')")
    public ModelAndView getOrderPage(Model model) {
        model.addAttribute("types", sysOrderService.queryAllTypes());
        model.addAttribute("userName", UserContext.currentUser().getRealName());
        model.addAttribute("phone", UserContext.currentUser().getPhone());
        return jumpPage(MODULE_PATH + "buy");
    }


    @PutMapping("order")
    @ApiOperation(value = "下单")
    @ResponseBody
    @PreAuthorize("hasPermission('/system/web/order','sys:web:order')")
    public Result order(@RequestBody SysOrder sysOrder) {

        return Result.decide(sysOrderService.insertOrder(sysOrder));
    }

    //    我的订单
    @GetMapping("getOrders")
    @ApiOperation(value = "我的订单")
    @PreAuthorize("hasPermission('/system/web/order','sys:web:order')")
    public ModelAndView getMyOrdersPage(Model model) {
        List<SysOrder> sysOrders = sysOrderService.queryOrderByUserId(UserContext.currentUser().getUserId());
        model.addAttribute("orders",sysOrders);
        return jumpPage(MODULE_PATH + "myOrders");
    }

//    跳转到订单数据列表
    @GetMapping("getOrderList")
    @ApiOperation(value = "跳转订单列表")
    @PreAuthorize("hasPermission('/system/web/order','sys:web:order')")
    public ModelAndView getOrderList(){
        return jumpPage("system/order/main");
    }


//    所有订单数据

    @RequestMapping("order/data")
    @ApiOperation(value = "获取订单数据")
    @PreAuthorize("hasPermission('/system/web/order','sys:web:order')")
    public ResultTable getAllOrderData(PageDomain pageDomain,SysOrder param){
        PageInfo<SysOrder> pageInfo=sysOrderService.queryAllOrders(param,pageDomain);
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
