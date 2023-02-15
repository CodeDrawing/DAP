package com.pearadmin.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.modules.sys.domain.SysOrder;
import com.pearadmin.modules.sys.domain.SysRole;
import com.pearadmin.modules.sys.domain.SysType;

import java.util.List;

public interface SysOrderService {

    boolean insertOrder(SysOrder sysOrder);
    List<SysType> queryAllTypes();
    //    根据用户获取列表
    List<SysOrder> queryOrderByUserId(String userId);
    PageInfo<SysOrder> queryAllOrders(SysOrder param, PageDomain pageDomain);

}
