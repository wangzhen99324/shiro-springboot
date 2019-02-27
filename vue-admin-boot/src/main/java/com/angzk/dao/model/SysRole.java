package com.angzk.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色别名
     */
    private String aliasName;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    private static final long serialVersionUID = 1L;
}