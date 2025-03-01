package com.amarsoft.sys.modular.role.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色授权资源树结果
 *
 * @author xuyuxiang
 * @date 2022/7/27 15:09
 **/
@Getter
@Setter
public class SysRoleGrantResourceTreeResult {

    /** 模块id */
    @ApiModelProperty(value = "模块id", position = 1)
    private String id;

    /** 模块名称*/
    @ApiModelProperty(value = "模块名称", position = 2)
    private String title;

    /** 模块图标 */
    @ApiModelProperty(value = "模块图标", position = 3)
    private String icon;

    /** 模块下菜单集合*/
    @ApiModelProperty(value = "模块下菜单集合", position = 4)
    private List<SysRoleGrantResourceMenuResult> menu;

    /**
     * 授权菜单类
     *
     * @author xuyuxiang
     * @date 2022/8/13 16:54
     */
    @Getter
    @Setter
    public static class SysRoleGrantResourceMenuResult {

        /** 菜单id */
        @ApiModelProperty(value = "菜单id", position = 1)
        private String id;

        /** 父id */
        @ApiModelProperty(value = "父id", position = 2)
        private String parentId;

        /** 父名称 */
        @ApiModelProperty(value = "菜单名称", position = 3)
        private String parentName;

        /** 标题 */
        @ApiModelProperty(value = "菜单标题", position = 4)
        private String title;

        /** 模块 */
        @ApiModelProperty(value = "菜单模块", position = 5)
        private String module;

        /** 菜单下按钮集合 */
        @ApiModelProperty(value = "菜单下按钮集合", position = 6)
        private List<SysRoleGrantResourceButtonResult> button;

        /**
         * 授权按钮类
         *
         * @author xuyuxiang
         * @date 2022/8/13 16:54
         */
        @Getter
        @Setter
        public static class SysRoleGrantResourceButtonResult {

            /** 按钮id */
            @ApiModelProperty(value = "按钮id", position = 1)
            private String id;

            /** 标题 */
            @ApiModelProperty(value = "按钮标题", position = 2)
            private String title;
        }
    }
}
