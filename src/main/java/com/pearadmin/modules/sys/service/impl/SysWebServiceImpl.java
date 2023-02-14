package com.pearadmin.modules.sys.service.impl;

import com.pearadmin.common.tools.SequenceUtil;
import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.domain.SysVisitData;
import com.pearadmin.modules.sys.mapper.SysWebMapper;
import com.pearadmin.modules.sys.service.SysWebService;
import lombok.val;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class SysWebServiceImpl implements SysWebService {
    @Autowired
    private SysWebMapper sysWebMapper;

    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public SysType queryType(String typeId) {
        System.err.println(typeId);
        SysType sysType = sysWebMapper.queryType(typeId);
        return sysType;
    }

    @Override
    public Boolean updateContent(SysType sysType) {
        sysType.setDate(new Date());
        sysType.setImageOne(sysType.getImageOne());
        sysType.setImageTwo(sysType.getImageTwo());
        int result = sysWebMapper.updateContent(sysType);
        if(result!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public SysVisitData queryCurrentVisitDate() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormatTwo = new SimpleDateFormat("MM-dd");
        Date date = new Date();
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
        Date currentDate=ca.getTime();




//        一天前
        ca.add(Calendar.DATE,-1);
        Date last1Day=ca.getTime();
//        两天前
        ca.add(Calendar.DATE,-1);

//        先查询有没有当天日期的记录
        SysVisitData sysVisitData = sysWebMapper.queryVisitData(sdf.format(new Date()));

        if(sysVisitData==null){
//            Is empty,so would insert a new visit record.
            SysVisitData insertData = new SysVisitData();
            insertData.setVisitId(SequenceUtil.makeStringId());
            insertData.setDate(new Date());
            sysWebMapper.insertVisitData(insertData);
            SysVisitData sysVisitData1 = sysWebMapper.queryVisitData(sdf.format(new Date()));
            return  sysVisitData1;
        }else{
//            have record,will query data again.
            SysVisitData sysVisitData2 = sysWebMapper.queryVisitData(sdf.format(new Date()));
            return  sysVisitData2;
        }

    }

    @Override
    public int visitIndex() {
        int result = sysWebMapper.visitIndex(sdf.format(new Date()));
        return result;
    }

    @Override
    public int visitOne() {
        int result = sysWebMapper.visitOne(sdf.format(new Date()));
        return result;
    }

    @Override
    public int visitTwo() {
        int result = sysWebMapper.visitTwo(sdf.format(new Date()));
        return result;
    }

    @Override
    public Integer querySumVisitIndexData() {
        return sysWebMapper.querySumVisitIndexData();
    }

}
