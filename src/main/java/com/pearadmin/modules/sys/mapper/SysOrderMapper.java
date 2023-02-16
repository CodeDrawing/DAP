package com.pearadmin.modules.sys.mapper;

import com.pearadmin.modules.sys.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysOrderMapper {

//    下单
    int insertOrder(SysOrder sysOrder);

//    获取类型列表
    List<SysType> queryAllTypes();

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

}
