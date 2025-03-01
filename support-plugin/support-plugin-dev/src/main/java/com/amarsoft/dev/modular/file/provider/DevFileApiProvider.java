package com.amarsoft.dev.modular.file.provider;

import com.amarsoft.dev.modular.file.service.DevFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amarsoft.dev.api.DevFileApi;
import com.amarsoft.dev.modular.file.enums.DevFileEngineTypeEnum;

import javax.annotation.Resource;

/**
 * 文件API接口提供者
 *
 * @author xuyuxiang
 * @date 2022/6/22 15:32
 **/
@Service
public class DevFileApiProvider implements DevFileApi {

    @Resource
    private DevFileService devFileService;

    @Override
    public String storageFileWithReturnUrlLocal(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.LOCAL.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdLocal(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.LOCAL.getValue(), file);
    }

    @Override
    public String storageFileWithReturnUrlAliyun(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.ALIYUN.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdAliyun(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.ALIYUN.getValue(), file);
    }

    @Override
    public String storageFileWithReturnUrlTencent(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.TENCENT.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdTencent(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.TENCENT.getValue(), file);
    }

    @Override
    public String storageFileWithReturnUrlMinio(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.MINIO.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdMinio(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.MINIO.getValue(), file);
    }
}
