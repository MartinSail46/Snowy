package com.amarsoft.auth.core.util;

import cn.dev33.satoken.exception.*;
import cn.hutool.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import com.amarsoft.common.pojo.CommonResult;
import com.amarsoft.common.util.CommonServletUtil;

@Slf4j
public class AuthExceptionUtil {

    /**
     * 根据错误类型获取对应的CommonResult（只处理SaToken相关异常）
     *
     * @author xuyuxiang
     * @date 2021/10/11 15:52
     **/
    public static CommonResult<String> getCommonResult(Exception e) {
        CommonResult<String> commonResult;
        if (e instanceof NotLoginException) {

            // 如果是未登录异常 401
            NotLoginException notLoginException = (NotLoginException) e;
            commonResult = CommonResult.get(HttpStatus.HTTP_UNAUTHORIZED, notLoginException.getMessage(), null);
        } else if (e instanceof NotRoleException) {

            // 如果是角色异常 403
            NotRoleException notRoleException = (NotRoleException) e;
            commonResult = CommonResult.get(HttpStatus.HTTP_FORBIDDEN, "无此角色：" + notRoleException.getRole() +
                    "，接口地址：" + CommonServletUtil.getRequest().getServletPath(), null);
        } else if (e instanceof NotPermissionException) {

            // 如果是权限异常 403
            NotPermissionException notPermissionException = (NotPermissionException) e;
            commonResult = CommonResult.get(HttpStatus.HTTP_FORBIDDEN, "无此权限：" + notPermissionException.getPermission(), null);
        } else if (e instanceof DisableServiceException) {

            // 如果是被封禁异常 403
            DisableServiceException disableServiceException = (DisableServiceException) e;
            commonResult = CommonResult.get(HttpStatus.HTTP_FORBIDDEN, "账号被封禁：" + disableServiceException.getDisableTime() + "秒后解封", null);
        } else if (e instanceof SaTokenException) {

            // 如果是SaToken异常 直接返回
            SaTokenException saTokenException = (SaTokenException) e;
            commonResult = CommonResult.error(saTokenException.getMessage());
        } else {
            // 未知异常才打印
            log.error(">>> 服务器未知异常，请求地址：{}，具体信息：", CommonServletUtil.getRequest().getRequestURL(), e);
            // 未知异常返回服务器异常（此处不可能执行进入，因为本方法处理的一定是SaToken的异常，此处仅为安全性考虑）
            commonResult = CommonResult.error();
        }
        return commonResult;
    }
}
