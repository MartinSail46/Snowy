package com.amarsoft.mobile.modular.resource.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 移动端菜单编辑参数
 *
 * @author yubaoshan
 * @date  2023/01/28 22:42
 **/
@Getter
@Setter
public class MobileMenuEditParam {

    /** 主键 */
    @ApiModelProperty(value = "主键", required = true, position = 1)
    @NotBlank(message = "id不能为空")
    private String id;

    /** 父ID */
    @ApiModelProperty(value = "父ID", required = true, position = 2)
    @NotBlank(message = "parentId不能为空")
    private String parentId;

    /** 名称 */
    @ApiModelProperty(value = "名称", required = true, position = 3)
    @NotBlank(message = "title不能为空")
    private String title;

    /** 分类 */
    @ApiModelProperty(value = "分类", required = true, position = 4)
    @NotBlank(message = "category不能为空")
    private String category;

    /** 模块 */
    @ApiModelProperty(value = "模块", required = true, position = 5)
    @NotBlank(message = "module不能为空")
    private String module;

    /** 菜单类型 */
    @ApiModelProperty(value = "菜单类型", position = 6)
    @NotBlank(message = "menuType不能为空")
    private String menuType;

    /** 路径 */
    @ApiModelProperty(value = "路径", position = 7)
    @NotBlank(message = "path不能为空")
    private String path;

    /** 图标 */
    @ApiModelProperty(value = "图标", required = true, position = 8)
    @NotBlank(message = "icon不能为空")
    private String icon;

    /** 颜色 */
    @ApiModelProperty(value = "颜色", required = true, position = 9)
    @NotBlank(message = "color不能为空")
    private String color;

    /** 规则类型 */
    @ApiModelProperty(value = "规则类型", required = true, position = 10)
    @NotBlank(message = "regType不能为空")
    private String regType;

    /** 可用状态 */
    @ApiModelProperty(value = "可用状态", required = true, position = 11)
    @NotBlank(message = "status不能为空")
    private String status;

    /** 排序码 */
    @ApiModelProperty(value = "排序码", position = 12)
    private Integer sortCode;
}
