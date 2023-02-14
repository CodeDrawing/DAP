package com.pearadmin.modules.sys.mapper;

import com.pearadmin.modules.sys.domain.SysOrder;
import com.pearadmin.modules.sys.domain.SysType;
import com.pearadmin.modules.sys.domain.SysVisitData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOrderMapper {

//    下单
    int insertOrder(SysOrder sysOrder);

//    获取类型列表
    List<SysType> queryAllTypes();

}
