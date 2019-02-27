package com.angzk.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class SysUserRole implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}