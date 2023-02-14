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
//    联系人
    String createBy;
//    所属人
    String executeBy;
    String phone;
    String address;
    Integer isNew;
    String comment;
    String orderId;
    String type;
}
