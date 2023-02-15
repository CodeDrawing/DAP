package com.pearadmin.modules.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysOrder {
    Date createDate;
//    创建人
    String createBy;
//联系人
    String contactPerson;

//    所属人
    String executeBy;
    String realName;
    String phone;
    String address;
    Integer isNew;
    String comment;
    String orderId;
    String typeId;
//    主题
    String subject;
//    项目名称
    String projectName;
//    类型名称
    String typeName;
    String orderProgressId;
}
