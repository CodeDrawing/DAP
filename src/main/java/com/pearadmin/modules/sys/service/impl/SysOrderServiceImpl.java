package com.pearadmin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.context.UserContext;
import com.pearadmin.common.tools.SequenceUtil;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.modules.sys.domain.SysOrder;
import com.pearadmin.modules.sys.domain.SysRole;
import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.mapper.SysOrderMapper;
import com.pearadmin.modules.sys.service.SysOrderService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SysOrderServiceImpl implements SysOrderService {
    @Autowired
    SysOrderMapper sysOrderMapper;
    @Override
    public boolean insertOrder(SysOrder sysOrder) {
        sysOrder.setCreateBy(UserContext.currentUser().getUserId());
        sysOrder.setCreateDate(new Date());
        sysOrder.setOrderId(SequenceUtil.makeStringId());
        sysOrder.setProjectName("请等待管理员命名");
        int result = sysOrderMapper.insertOrder(sysOrder);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<SysType> queryAllTypes() {
        List<SysType> sysTypes = sysOrderMapper.queryAllTypes();
        return sysTypes;
    }

    @Override
    public List<SysOrder> queryOrderByUserId(String userId) {
        List<SysOrder> sysOrders = sysOrderMapper.queryOrderByUserId(userId);
        return sysOrders;
    }

    @Override
    public PageInfo<SysOrder> queryAllOrders(SysOrder param, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
        List<SysOrder> sysOrders = sysOrderMapper.queryAllOrders(param);
        return new PageInfo<>(sysOrders);
    }
}
