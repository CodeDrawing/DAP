package com.pearadmin.modules.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysOrderProgress {
    String orderId;
    String orderProgressId;
    String comment;
    String createDate;
    String createBy;
    String projectName;

}
