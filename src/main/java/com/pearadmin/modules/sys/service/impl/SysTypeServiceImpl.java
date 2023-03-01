package com.pearadmin.modules.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.context.UserContext;
import com.pearadmin.common.tools.SequenceUtil;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.mapper.SysOrderMapper;
import com.pearadmin.modules.sys.service.SysTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SysTypeServiceImpl implements SysTypeService {

    @Autowired
    SysOrderMapper sysOrderMapper;
    @Override
    public PageInfo<SysType> queryAllTypes(SysType param, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
        List<SysType> sysTypes = sysOrderMapper.queryAllTypes(param);
        System.err.println(sysTypes);
        return new PageInfo<>(sysTypes);
    }

    @Override
    public boolean addType(SysType sysType) {
        sysType.setCreateBy(UserContext.currentUser().getUserId());
        sysType.setCreateDate(new Date());
        sysType.setTypeId(SequenceUtil.makeStringId());
        int result = sysOrderMapper.addType(sysType);
        if(result==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateIsShow(SysType sysType) {
        int result = sysOrderMapper.updateIsShow(sysType);
        if (result == 1) {
            return true;
        } else {
            return false;

        }
    }
}
