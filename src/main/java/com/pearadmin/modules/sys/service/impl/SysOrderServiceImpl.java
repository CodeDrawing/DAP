package com.pearadmin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.context.UserContext;
import com.pearadmin.common.tools.SequenceUtil;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.modules.sys.domain.SysOrder;
import com.pearadmin.modules.sys.domain.SysOrderProgress;
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


        SysOrderProgress sysOrderProgress = new SysOrderProgress();
        sysOrderProgress.setOrderId(sysOrder.getOrderId());
        sysOrderProgress.setCreateBy(UserContext.currentUser().getUserId());
        sysOrderProgress.setOrderProgressId(SequenceUtil.makeStringId());
        sysOrderProgress.setCreateDate(new Date());
        sysOrderProgress.setComment("无");
        sysOrderProgress.setTitle(UserContext.currentUser().getRealName()+"创建了改订单");
        addOrderProgress(sysOrderProgress);

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

    @Override
    public boolean editOrder(SysOrder sysOrder) {
        int result = sysOrderMapper.editOrder(sysOrder);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public SysOrder queryOrderByOrderId(String orderId) {
        SysOrder sysOrder = sysOrderMapper.queryOrderByOrderId(orderId);
        return sysOrder;
    }

    @Override
    public boolean deleteOrder(String orderId) {
        sysOrderMapper.deleteOrder(orderId);

            return true;

    }

    @Override
    public boolean addOrderProgress(SysOrderProgress sysOrderProgress) {
        int result = sysOrderMapper.addOrderProgress(sysOrderProgress);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }
}
