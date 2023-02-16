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
public class SysOrderProgress {
    String orderId;
    String orderProgressId;
    String comment;
    Date createDate;
    String createBy;
    String projectName;
    String title;
}
