package com.xiaoqian.blog.controller;


import com.xiaoqian.common.domain.ResponseResult;
import com.xiaoqian.common.domain.vo.LinkVo;
import com.xiaoqian.common.service.ILinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 友链 前端控制器
 * </p>
 *
 * @author QianCCC
 * @since 2024-01-27
 */
@RestController
@RequestMapping("/link")
@Api(tags = "友链相关接口")
@RequiredArgsConstructor
public class LinkController {
    private final ILinkService linkService;

    @ApiOperation("查询所有审核通过的友链列表")
    @GetMapping("/getAllLink")
    public ResponseResult<List<LinkVo>> getAllLink() {
        return linkService.getAllLink();
    }
}
