package com.pearadmin.modules.sys.controller;

import com.pearadmin.common.aop.annotation.Log;
import com.pearadmin.common.aop.enums.BusinessType;
import com.pearadmin.common.context.UserContext;
import com.pearadmin.common.web.base.BaseController;
import com.pearadmin.common.secure.session.SecureSessionService;
import com.pearadmin.modules.sys.domain.SysVisitData;
import com.pearadmin.modules.sys.service.SysWebService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Describe: 入 口 控 制 器
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@RestController
@RequestMapping
@Api(tags = {"项目入口"})
public class SysEntranceController extends BaseController {

    @Resource
    private SessionRegistry sessionRegistry;

    @Autowired
    SysWebService sysWebService;

    /**
     * Describe: 获取登录视图
     * Param: ModelAndView
     * Return: 登录视图
     */
    @GetMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        if (UserContext.isAuthentication()) {
            SecureSessionService.expiredSession(request, sessionRegistry);
            return jumpPage("indexInfo");
        } else {
            return jumpPage("login");
        }
    }

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 登录视图
     */
    @GetMapping("indexInfo")
    @Log(title = "主页", describe = "返回 Index 主页视图", type = BusinessType.ADD)
    public ModelAndView indexInfo() {
        return jumpPage("indexInfo");
    }

    /**
     * Describe: 获取网址首页
     * Param: ModelAndView
     * Return: 登录视图
     */
    @GetMapping("index")
    @Log(title = "首页", describe = "返回 index 首页视图", type = BusinessType.ADD)
    public ModelAndView index() {
        sysWebService.visitIndex();
        return jumpPage("index");
    }

    /**
     * Describe: 获取网址首页
     * Param: ModelAndView
     * Return: 登录视图
     */
    @GetMapping("/")
    @Log(title = "首页", describe = "返回 index 首页视图", type = BusinessType.ADD)
    public ModelAndView indexOtherWay() {
        sysWebService.visitIndex();
        return jumpPage("index");
    }

    /**
     * Describe: 获取主页视图
     * Param: ModelAndView
     * Return: 主页视图
     */
    @GetMapping("console")
    public ModelAndView home(Model model) {
        SysVisitData sysVisitData = sysWebService.queryCurrentVisitDate();
//        当日首页访问量
        model.addAttribute("todayVisitIndex",sysVisitData.getTypeIndex());
//         网站总访问量
        model.addAttribute("sumVisitIndex",sysWebService.querySumVisitIndexData());
        return jumpPage("console/console");
    }

    /**
     * Describe:无权限页面
     * Return:返回403页面
     */
    @GetMapping("error/403")
    public ModelAndView noPermission() {
        return jumpPage("error/403");
    }

    /**
     * Describe:找不带页面
     * Return:返回404页面
     */
    @GetMapping("error/404")
    public ModelAndView notFound() {
        return jumpPage("error/404");
    }

    /**
     * Describe:异常处理页
     * Return:返回500界面
     */
    @GetMapping("error/500")
    public ModelAndView onException() {
        return jumpPage("error/500");
    }

}
