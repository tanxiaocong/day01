package com.vsc.website.controller.backend;

import com.vsc.website.common.MessageCode;
import com.vsc.website.common.PageBean;
import com.vsc.website.common.ResultObject;
import com.vsc.website.common.Util;
import com.vsc.website.qo.backend.BProgramAddQo;
import com.vsc.website.service.ProgramService;
import com.vsc.website.vo.backend.BProgramDetailVo;
import com.vsc.website.vo.backend.BProgramListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Api(tags = {"后台-解决方案-接口"})
@RestController
@RequestMapping("/backend/program")
public class BProgramController {

    @Resource
    private ProgramService programService;

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除解决方案")
    @ResponseBody
    public ResultObject delete(
            HttpServletRequest request,
            @ApiParam("解决方案id") @PathVariable Integer id
    )throws Exception{
        programService.delete(id, Util.getLoginToken(request));
        return  new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加解决方案")
    @ResponseBody
    public ResultObject add(
            HttpServletRequest request,
            @Valid @RequestBody BProgramAddQo qo,
            BindingResult errors) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, IOException {
        if (errors.hasErrors()) {
            return new ResultObject<>(MessageCode.CODE_PARAMETER_ERROR, null, errors.getAllErrors());
        }
        programService.add(qo,Util.getLoginToken(request));
        return  new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "解决方案列表")
    @ResponseBody
    public ResultObject<PageBean<BProgramListVo>> list(
            HttpServletRequest request,
            @ApiParam("关键字") @RequestParam(required = false) String keywords,
            @ApiParam("行业id") @RequestParam(required = false) Integer industryId,
            @ApiParam("页数") @RequestParam(required = true) int page,
            @ApiParam("数量") @RequestParam(required = true) int size
    )throws Exception{
        keywords=Util.replaceStrParam(keywords);
        return new ResultObject<>(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage(),
                programService.list(keywords,industryId,page,size,Util.getLoginToken(request)));
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ApiOperation(value = "解决方案详情")
    @ResponseBody
    public ResultObject<BProgramDetailVo> detail(
            HttpServletRequest request,
            @ApiParam("解决方案id") @PathVariable Integer id
    )throws Exception{
        return  new ResultObject<>(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage(),
                programService.detail(id,Util.getLoginToken(request),null));
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    @ApiOperation(value = "更新解决方案")
    @ResponseBody
    public ResultObject update(
            HttpServletRequest request,
            @ApiParam("解决方案id") @PathVariable Integer id,
            @Valid @RequestBody BProgramAddQo qo,
            BindingResult errors
    )throws Exception{
        programService.update(qo,id,Util.getLoginToken(request));
        return new ResultObject(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage());
    }

    @RequestMapping(value = "/CheckHomeLocation", method = RequestMethod.GET)
    @ApiOperation(value = "校验首页位置")
    @ResponseBody
    public void CheckHomeLocation(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam("首页位置") @RequestParam(required = true) Integer homeLocation,
            @ApiParam("方案id") @RequestParam(required = false) Integer programId
    )throws Exception{
//        return new ResultObject<>(MessageCode.CODE_SUCCESS,Util.getLoginToken(request).getLanguage(),
//                programService.checkHomeLocation(homeLocation,programId));
        response.getWriter().println(JSONObject.fromObject(new ResultObject(MessageCode.CODE_SUCCESS,
                Util.getLoginToken(request).getLanguage(), programService.checkHomeLocation(homeLocation,programId))).toString());
    }
}
