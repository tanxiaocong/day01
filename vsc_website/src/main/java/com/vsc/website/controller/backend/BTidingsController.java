package com.vsc.website.controller.backend;

import com.vsc.website.common.MessageCode;
import com.vsc.website.common.PageBean;
import com.vsc.website.common.ResultObject;
import com.vsc.website.common.Util;
import com.vsc.website.qo.backend.BProgramAddQo;
import com.vsc.website.qo.backend.BTidingsAddQo;
import com.vsc.website.service.ProgramService;
import com.vsc.website.service.TidingsService;
import com.vsc.website.vo.backend.BProgramDetailVo;
import com.vsc.website.vo.backend.BProgramListVo;
import com.vsc.website.vo.backend.BTidingsDetailVo;
import com.vsc.website.vo.backend.BTidingsListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Api(tags = {"后台-新闻管理-接口"})
@RestController
@RequestMapping("/backend/tidings")
public class BTidingsController {
    @Resource
    private TidingsService tidingsService;

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除新闻")
    @ResponseBody
    public ResultObject delete(
            HttpServletRequest request,
            @ApiParam("解决方案id") @PathVariable Integer id
    )throws Exception{
        tidingsService.delete(id, Util.getLoginToken(request));
        return  new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加新闻")
    @ResponseBody
    public ResultObject add(
            HttpServletRequest request,
            @Valid @RequestBody BTidingsAddQo qo,
            BindingResult errors) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        if (errors.hasErrors()) {
            return new ResultObject<>(MessageCode.CODE_PARAMETER_ERROR, null, errors.getAllErrors());
        }
        tidingsService.add(qo,Util.getLoginToken(request));
        return  new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "新闻列表")
    @ResponseBody
    public ResultObject<PageBean<BTidingsListVo>> list(
            HttpServletRequest request,
            @ApiParam("关键字") @RequestParam(required = false) String keywords,
            @ApiParam("页数") @RequestParam(required = true) int page,
            @ApiParam("数量") @RequestParam(required = true) int size
    )throws Exception{
        keywords=Util.replaceStrParam(keywords);
        return new ResultObject<>(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage(),
                tidingsService.list(keywords,Util.getLoginToken(request),page,size));
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ApiOperation(value = "新闻详情")
    @ResponseBody
    public ResultObject<BTidingsDetailVo> detail(
            HttpServletRequest request,
            @ApiParam("新闻id") @PathVariable Integer id
    )throws Exception{
        return  new ResultObject<>(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage(),
                tidingsService.detail(id,Util.getLoginToken(request)));
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新新闻")
    @ResponseBody
    public ResultObject update(
            HttpServletRequest request,
            @ApiParam("新闻id") @PathVariable Integer id,
            @Valid @RequestBody BTidingsAddQo qo,
            BindingResult errors
    )throws Exception{
        tidingsService.update(qo,id,Util.getLoginToken(request));
        return new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }
}
