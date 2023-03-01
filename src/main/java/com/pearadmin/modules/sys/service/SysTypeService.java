package com.pearadmin.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.modules.sys.domain.SysType;

import java.util.List;

public interface SysTypeService {
    //    获取类型列表
    PageInfo<SysType> queryAllTypes(SysType param, PageDomain pageDomain);


    boolean addType(SysType sysType);

    boolean updateIsShow(SysType sysType);
}
