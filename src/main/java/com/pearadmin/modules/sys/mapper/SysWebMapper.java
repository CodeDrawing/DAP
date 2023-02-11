package com.pearadmin.modules.sys.mapper;

import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.domain.SysVisitData;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface SysWebMapper {
    SysType queryType(String typeId);
    int updateContent(SysType sysType);

    //访问数据

//    查询当天的访问量
    SysVisitData queryVisitData(String date);
//    新增当天的访问记录
    int insertVisitData(SysVisitData insertData);

//    新增首页访问量
    int visitIndex(String date);
//    新增壁画访问量
    int visitOne(String date);
//    新增秸秆画访问量
    int visitTwo(String date);
}
