package com.amarsoft.mobile.modular.resource.provider;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;
import com.amarsoft.mobile.api.MobileMenuApi;
import com.amarsoft.mobile.modular.resource.service.MobileMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 移动端菜单API接口提供者
 *
 * @author xuyuxiang
 * @date 2023/1/31 10:12
 **/
@Service
public class MobileMenuApiProvider implements MobileMenuApi {

    @Resource
    private MobileMenuService mobileMenuService;

    @Override
    public List<JSONObject> mobileMenuTreeSelector() {
        return mobileMenuService.mobileMenuTreeSelector();
    }

    @Override
    public List<Tree<String>> loginMobileMenuTree(List<String> menuIdList) {
        return mobileMenuService.loginMobileMenuTree(menuIdList);
    }
}
