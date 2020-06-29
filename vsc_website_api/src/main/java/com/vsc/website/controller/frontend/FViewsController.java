package com.vsc.website.controller.frontend;

import com.vsc.website.common.*;
import com.vsc.website.service.TidingsService;
import com.vsc.website.vo.backend.BTidingsDetailVo;
import com.vsc.website.vo.frontend.FHomePageViewsVo;
import com.vsc.website.vo.frontend.FViewsDetailVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "前台-新闻-接口")
@RestController
@RequestMapping("/frontend/views")
public class FViewsController {

    @Resource
    private TidingsService tidingsService;

    @RequestMapping(value = "/getViewsTimeNew", method = RequestMethod.GET)
    @ApiOperation(value = "前台新闻时间")
    @ResponseBody
    public ResultObject<List<String>> getViewsTimeNew(
            HttpServletRequest request
    )throws Exception{
        return new ResultObject<>(MessageCode.CODE_SUCCESS,null,tidingsService.getViewsTimeNew());
    }

    @RequestMapping(value = "/getViewsTimeOld", method = RequestMethod.GET)
    @ApiOperation(value = "前台新闻时间(更早)")
    @ResponseBody
    public ResultObject<List<String>> getViewsTimeOld(
            HttpServletRequest request
    )throws Exception{
        return new ResultObject<>(MessageCode.CODE_SUCCESS,null,tidingsService.getViewsTimeOld());
    }

    @RequestMapping(value = "/frontendList", method = RequestMethod.GET)
    @ApiOperation(value = "前台新闻列表")
    @ResponseBody
    public ResultObject<List<FHomePageViewsVo>> frontendList(
            HttpServletRequest request,
            @ApiParam("年份")@RequestParam(required = false) Integer year,
            @ApiParam("语言") @RequestParam(required = false) Integer language
    )throws Exception{
        language=language==null? Constant.CHINESE :language;
        return new ResultObject<>(MessageCode.CODE_SUCCESS,language,tidingsService.frontendList(year,language));
    }

    @RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
    @ApiOperation(value = "新闻详情")
    @ResponseBody
    public ResultObject<FViewsDetailVo> detail(
            HttpServletRequest request,
            @ApiParam("语言") @RequestParam(required = false) Integer language,
            @ApiParam("新闻id") @PathVariable Integer id
    )throws Exception{
        language=language==null? Constant.CHINESE :language;
        return  new ResultObject<>(MessageCode.CODE_SUCCESS, language,tidingsService.frontendDetail(id,language));
    }
}
