package com.amarsoft.sys.modular.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amarsoft.common.annotation.CommonLog;
import com.amarsoft.common.pojo.CommonResult;
import com.amarsoft.sys.modular.resource.entity.SysButton;
import com.amarsoft.sys.modular.resource.param.button.SysButtonAddParam;
import com.amarsoft.sys.modular.resource.param.button.SysButtonEditParam;
import com.amarsoft.sys.modular.resource.param.button.SysButtonIdParam;
import com.amarsoft.sys.modular.resource.param.button.SysButtonPageParam;
import com.amarsoft.sys.modular.resource.service.SysButtonService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 按钮控制器
 *
 * @author xuyuxiang
 * @date 2022/6/27 13:56
 **/
@Api(tags = "按钮控制器")
@ApiSupport(author = "SUPPORT_TEAM", order = 3)
@RestController
@Validated
public class SysButtonController {

    @Resource
    private SysButtonService sysButtonService;

    /**
     * 获取按钮分页
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:00
     */
    @ApiOperationSupport(order = 1)
    @ApiOperation("获取按钮分页")
    @GetMapping("/sys/button/page")
    public CommonResult<Page<SysButton>> page(SysButtonPageParam sysButtonPageParam) {
        return CommonResult.data(sysButtonService.page(sysButtonPageParam));
    }

    /**
     * 添加按钮
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:47
     */
    @ApiOperationSupport(order = 2)
    @ApiOperation("添加按钮")
    @CommonLog("添加按钮")
    @PostMapping("/sys/button/add")
    public CommonResult<String> add(@RequestBody @Valid SysButtonAddParam sysButtonAddParam) {
        sysButtonService.add(sysButtonAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑按钮
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:47
     */
    @ApiOperationSupport(order = 3)
    @ApiOperation("编辑按钮")
    @CommonLog("编辑按钮")
    @PostMapping("/sys/button/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysButtonEditParam sysButtonEditParam) {
        sysButtonService.edit(sysButtonEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除按钮
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:00
     */
    @ApiOperationSupport(order = 4)
    @ApiOperation("删除按钮")
    @CommonLog("删除按钮")
    @PostMapping("/sys/button/delete")
    public CommonResult<String> delete(@RequestBody @Valid List<SysButtonIdParam> sysButtonIdParamList) {
        sysButtonService.delete(sysButtonIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取按钮详情
     *
     * @author xuyuxiang
     * @date 2022/4/24 20:00
     */
    @ApiOperationSupport(order = 5)
    @ApiOperation("获取按钮详情")
    @GetMapping("/sys/button/detail")
    public CommonResult<SysButton> detail(@Valid SysButtonIdParam sysButtonIdParam) {
        return CommonResult.data(sysButtonService.detail(sysButtonIdParam));
    }
}
