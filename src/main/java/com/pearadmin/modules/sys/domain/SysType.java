package com.pearadmin.modules.sys.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysType {
    String typeId;
    String typeName;
    String paragraphOne;
    String abstractOne;
    String paragraphTwo;
    String abstractTwo;
    String imageOne;
    String imageTwo;
    String isShow;
    String createBy;
    String realName;
    Date createDate;
    Date editDate;
}
