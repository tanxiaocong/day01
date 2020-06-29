package com.vsc.website.controller.frontend;

import com.vsc.website.common.Constant;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.ResultObject;
import com.vsc.website.common.Util;
import com.vsc.website.service.DictService;
import com.vsc.website.service.ProgramService;
import com.vsc.website.vo.backend.BProgramDetailVo;
import com.vsc.website.vo.frontend.FDictIndustryVo;
import com.vsc.website.vo.frontend.FProgramViewVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = {"前台-首页-接口"})
@RestController
@RequestMapping("/frontend/home")
public class FHomePageController {

    @Resource
    private DictService dictService;

    @Resource
    private ProgramService programService;

    @RequestMapping(value = "/industryList", method = RequestMethod.GET)
    @ApiOperation(value = "首页行业-方案列表")
    @ResponseBody
    public ResultObject<List<FDictIndustryVo>> industryList(
            HttpServletRequest request,
            @ApiParam("语言") @RequestParam(required = false) Integer language
    )throws Exception{
        language=language==null? Constant.CHINESE :language;
        return new ResultObject<>(MessageCode.CODE_SUCCESS,language,dictService.industryVoList(language));
    }

    @RequestMapping(value = "/getHomeViewProgram", method = RequestMethod.GET)
    @ApiOperation(value = "首页展示方案")
    @ResponseBody
    public ResultObject<List<FProgramViewVo>> getHomeViewProgram(
            HttpServletRequest request,
            @ApiParam("语言") @RequestParam(required = false) Integer language
    )throws Exception{
        language=language==null? Constant.CHINESE :language;
        return new ResultObject<>(MessageCode.CODE_SUCCESS,language,programService.getHomeView(language));
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ApiOperation(value = "解决方案详情")
    @ResponseBody
    public ResultObject<BProgramDetailVo> detail(
            HttpServletRequest request,
            @ApiParam("解决方案id") @PathVariable Integer id,
            @ApiParam("语言") @RequestParam(required = false) Integer language
    )throws Exception{
        language=language==null? Constant.CHINESE :language;
        return  new ResultObject<>(MessageCode.CODE_SUCCESS, language,
                programService.detail(id,Util.getLoginToken(request),language));
    }
}
