package com.amarsoft.sys.modular.user.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 用户修改密码参数
 *
 * @author xuyuxiang
 * @date 2022/7/26 16:04
 **/
@Getter
@Setter
public class SysUserUpdatePwdParam {

    /** 旧密码 */
    @ApiModelProperty(value = "旧密码", required = true, position = 2)
    @NotBlank(message = "password不能为空")
    private String password;

    /** 新密码 */
    @ApiModelProperty(value = "新密码", required = true, position = 3)
    @NotBlank(message = "newPassword不能为空")
    private String newPassword;
}
