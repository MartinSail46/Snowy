package com.amarsoft.dev.modular.config.provider;

import com.amarsoft.dev.modular.config.service.DevConfigService;
import org.springframework.stereotype.Service;
import com.amarsoft.dev.api.DevConfigApi;

import javax.annotation.Resource;

/**
 * 配置API接口实现类
 *
 * @author xuyuxiang
 * @date 2022/6/17 14:43
 **/
@Service
public class DevConfigApiProvider implements DevConfigApi {

    @Resource
    private DevConfigService devConfigService;

    @Override
    public String getValueByKey(String key) {
        return devConfigService.getValueByKey(key);
    }
}
