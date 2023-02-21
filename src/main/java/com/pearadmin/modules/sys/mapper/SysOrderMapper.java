package com.pearadmin.modules.sys.mapper;

import com.pearadmin.modules.sys.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOrderMapper {

//    下单
    int insertOrder(SysOrder sysOrder);

//    获取类型列表
    List<SysType> queryAllTypes(SysType param);

//    根据用户获取列表
    List<SysOrder> queryOrderByUserId(String userId);

    List<SysOrder> queryAllOrders(SysOrder param);

    int editOrder(SysOrder sysOrder);

    SysOrder queryOrderByOrderId(String orderId);

    /**
     * 修改is_delete为1 表示删除
     * @param orderId
     * @return
     */
    int deleteOrder(String orderId);

//    新增订单进度
    int addOrderProgress(SysOrderProgress sysOrderProgress);
//    查询进度信息根据项目Id
    List<SysOrderProgress> queryOrderProressByUsreId(String orderId);

//    修改项目是否为新项目（已联系)
    int updateOrderIsNew(SysOrder sysOrder);

    //    修改项目是否为已经完工
    int updateOrderIsFinish(SysOrder sysOrder);
//    项目总数
    int countOrder();
//    显示所有isShow为1的类型
    List<SysType> queryIsShowTypes();
}
