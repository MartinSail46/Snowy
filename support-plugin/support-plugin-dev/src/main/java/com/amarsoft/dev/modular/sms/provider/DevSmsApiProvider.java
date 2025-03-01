package com.amarsoft.dev.modular.sms.provider;

import com.amarsoft.dev.modular.sms.service.DevSmsService;
import org.springframework.stereotype.Service;
import com.amarsoft.dev.api.DevSmsApi;
import com.amarsoft.dev.modular.sms.param.DevSmsSendAliyunParam;
import com.amarsoft.dev.modular.sms.param.DevSmsSendTencentParam;

import javax.annotation.Resource;

/**
 * 短信API接口提供者
 *
 * @author xuyuxiang
 * @date 2022/6/22 15:33
 **/
@Service
public class DevSmsApiProvider implements DevSmsApi {

    @Resource
    private DevSmsService devSmsService;

    @Override
    public void sendSmsAliyun(String phoneNumbers, String signName, String templateCode, String templateParam) {
        DevSmsSendAliyunParam devSmsSendAliyunParam = new DevSmsSendAliyunParam();
        devSmsSendAliyunParam.setPhoneNumbers(phoneNumbers);
        devSmsSendAliyunParam.setSignName(signName);
        devSmsSendAliyunParam.setTemplateCode(templateCode);
        devSmsSendAliyunParam.setTemplateParam(templateParam);
        devSmsService.sendAliyun(devSmsSendAliyunParam);
    }

    @Override
    public void sendSmsTencent(String sdkAppId, String phoneNumbers, String signName, String templateCode, String templateParam) {
        DevSmsSendTencentParam devSmsSendTencentParam = new DevSmsSendTencentParam();
        devSmsSendTencentParam.setSdkAppId(sdkAppId);
        devSmsSendTencentParam.setPhoneNumbers(phoneNumbers);
        devSmsSendTencentParam.setSignName(signName);
        devSmsSendTencentParam.setTemplateCode(templateCode);
        devSmsSendTencentParam.setTemplateParam(templateParam);
        devSmsService.sendTencent(devSmsSendTencentParam);
    }
}
