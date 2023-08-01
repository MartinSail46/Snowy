package com.amarsoft.auth.modular.third.service;

import com.amarsoft.auth.modular.third.result.AuthThirdRenderResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthCallback;
import com.amarsoft.auth.modular.third.entity.AuthThirdUser;
import com.amarsoft.auth.modular.third.param.AuthThirdCallbackParam;
import com.amarsoft.auth.modular.third.param.AuthThirdRenderParam;
import com.amarsoft.auth.modular.third.param.AuthThirdUserPageParam;

/**
 * 第三方登录Service接口
 *
 * @author xuyuxiang
 * @date 2022/7/8 16:20
 **/
public interface AuthThirdService extends IService<AuthThirdUser> {

    /**
     * 第三方登录页面渲染，返回授权结果
     *
     * @author xuyuxiang
     * @date 2022/7/8 16:37
     **/
    AuthThirdRenderResult render(AuthThirdRenderParam authThirdRenderParam);

    /**
     * 第三方登录授权回调，返回登录token
     *
     * @author xuyuxiang
     * @date 2022/7/8 16:42
     **/
    String callback(AuthThirdCallbackParam authThirdCallbackParam, AuthCallback authCallback);

    /**
     * 获取三方用户分页
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:08
     */
    Page<AuthThirdUser> page(AuthThirdUserPageParam authThirdUserPageParam);
}
