package com.pearadmin.modules.sys.service;

import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.domain.SysVisitData;

import java.util.Date;

public interface SysWebService {
    SysType queryType(String typeName);
    Boolean updateContent(SysType sysType);
//    获取当天的访问记录
    SysVisitData queryCurrentVisitDate();

    //    新增首页访问量
    int visitIndex();
    //    新增壁画访问量
    int visitOne();
    //    新增秸秆画访问量
    int visitTwo();
    //    查询总的首页访问量
    Integer querySumVisitIndexData();

}
