package com.amarsoft.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 编辑个人工作台参数
 *
 * @author xuyuxiang
 * @date 2022/7/27 17:08
 **/
@Getter
@Setter
public class SysUserUpdateWorkbenchParam {

    /** 工作台数据 */
    @ApiModelProperty(value = "工作台数据", required = true)
    @NotBlank(message = "workbenchData不能为空")
    private String workbenchData;
}
