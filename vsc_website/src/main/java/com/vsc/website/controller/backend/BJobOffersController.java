package com.vsc.website.controller.backend;

import com.vsc.website.common.MessageCode;
import com.vsc.website.common.PageBean;
import com.vsc.website.common.ResultObject;
import com.vsc.website.common.Util;
import com.vsc.website.qo.backend.BJobOffersAddQo;
import com.vsc.website.service.JobOffersService;
import com.vsc.website.vo.backend.BJobOffersDetailVo;
import com.vsc.website.vo.backend.BJobOffersListVo;
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

@Api(tags = {"后台-招聘岗位-接口"})
@RestController
@RequestMapping("/backend/jobOffers")
public class BJobOffersController {

    @Resource
    private JobOffersService jobOffersService;

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除招聘岗位")
    @ResponseBody
    public ResultObject delete(
            HttpServletRequest request,
            @ApiParam("招聘岗位id") @PathVariable Integer id
    )throws Exception{
        jobOffersService.delete(id, Util.getLoginToken(request));
        return  new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加招聘岗位")
    @ResponseBody
    public ResultObject add(
            HttpServletRequest request,
            @Valid @RequestBody BJobOffersAddQo qo,
            BindingResult errors) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        if (errors.hasErrors()) {
            return new ResultObject<>(MessageCode.CODE_PARAMETER_ERROR, null, errors.getAllErrors());
        }
        jobOffersService.add(qo,Util.getLoginToken(request));
        return  new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "招聘岗位列表")
    @ResponseBody
    public ResultObject<PageBean<BJobOffersListVo>> list(
            HttpServletRequest request,
            @ApiParam("关键字") @RequestParam(required = false) String keywords,
            @ApiParam("页数") @RequestParam(required = true) int page,
            @ApiParam("数量") @RequestParam(required = true) int size
    )throws Exception{
        keywords=Util.replaceStrParam(keywords);
        return new ResultObject<>(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage(),
                jobOffersService.list(keywords,Util.getLoginToken(request),page,size));
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ApiOperation(value = "招聘岗位详情")
    @ResponseBody
    public ResultObject<BJobOffersDetailVo> detail(
            HttpServletRequest request,
            @ApiParam("招聘岗位id") @PathVariable Integer id
    )throws Exception{
        return  new ResultObject<>(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage(),
                jobOffersService.detail(id,Util.getLoginToken(request)));
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新招聘岗位")
    @ResponseBody
    public ResultObject update(
            HttpServletRequest request,
            @ApiParam("招聘岗位id") @PathVariable Integer id,
            @Valid @RequestBody BJobOffersAddQo qo,
            BindingResult errors
    )throws Exception{
        jobOffersService.update(qo,id,Util.getLoginToken(request));
        return new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }
}
