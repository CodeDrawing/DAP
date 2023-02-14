package com.pearadmin.modules.sys.service;

import com.pearadmin.modules.sys.domain.SysOrder;
import com.pearadmin.modules.sys.domain.SysType;

import java.util.List;

public interface SysOrderService {

    boolean insertOrder(SysOrder sysOrder);
    List<SysType> queryAllTypes();

}
