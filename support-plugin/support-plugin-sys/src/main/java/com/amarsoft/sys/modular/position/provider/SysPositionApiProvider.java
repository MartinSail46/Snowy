package com.amarsoft.sys.modular.position.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.amarsoft.sys.modular.position.service.SysPositionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import com.amarsoft.sys.api.SysPositionApi;
import com.amarsoft.sys.modular.position.param.SysPositionSelectorPositionParam;

import javax.annotation.Resource;

/**
 * 职位API接口提供者
 *
 * @author xuyuxiang
 * @date 2022/7/22 14:56
 **/
@Service
public class SysPositionApiProvider implements SysPositionApi {

    @Resource
    private SysPositionService sysPositionService;

    @Override
    public String getNameById(String positionId) {
        return sysPositionService.queryEntity(positionId).getName();
    }

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> positionSelector(String orgId, String searchKey) {
        SysPositionSelectorPositionParam sysPositionSelectorPositionParam = new SysPositionSelectorPositionParam();
        sysPositionSelectorPositionParam.setOrgId(orgId);
        sysPositionSelectorPositionParam.setSearchKey(searchKey);
        return BeanUtil.toBean(sysPositionService.positionSelector(sysPositionSelectorPositionParam), Page.class);
    }
}
