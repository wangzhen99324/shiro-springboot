package com.angzk.dao.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class SysMenu implements Serializable {
    /**
     * 菜单/按钮id
     */
    private Long menuId;

    /**
     * 上级菜单id
     */
    private Long parentId;

    /**
     * 菜单/按钮名称
     */
    private String menuName;

    /**
     * 对应路由path
     */
    private String path;

    /**
     * 对应路由组件component
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    private String type;

    /**
     * 排序
     */
    private Double orderNum;

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