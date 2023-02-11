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
public class SysVisitData {

    Date date;
    Integer typeOne;
    String visitId;
    Integer typeTwo;
    Integer typeIndex;
}
