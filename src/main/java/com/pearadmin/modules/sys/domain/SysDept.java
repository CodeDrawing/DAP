package com.pearadmin.modules.sys.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pearadmin.common.web.base.BaseDomain;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * Describe: 字典值领域模型
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
@Data
@Alias("SysDept")
public class SysDept extends BaseDomain {

    /**
     * 部门编号
     */
    @TableId
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门地址
     */
    private String address;

    /**
     * 父级编号
     */
    private String parentId;


    /**
     * 负责人
     */
    private String leader;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 计算列 提供给前端组件
     */
    @TableField(exist = false)
    private String checkArr = "0";

}
