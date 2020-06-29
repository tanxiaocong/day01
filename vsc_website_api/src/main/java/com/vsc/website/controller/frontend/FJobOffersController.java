package com.vsc.website.controller.frontend;

import com.vsc.website.common.*;
import com.vsc.website.qo.frontend.SendResumeMailQo;
import com.vsc.website.service.JobOffersService;
import com.vsc.website.vo.frontend.FJobOffersListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "前台-招聘-接口")
@RestController
@RequestMapping("/frontend/jobOffers")
public class FJobOffersController {

    @Resource
    private JobOffersService jobOffersService;


    @RequestMapping(value = "/frontendList", method = RequestMethod.GET)
    @ApiOperation("招聘列表")
    @ResponseBody
    public ResultObject<List<FJobOffersListVo>> frontendList(
            HttpServletRequest request,
            @ApiParam("招聘类型0：社会招聘 1：实习生招聘") @RequestParam Integer typeId,
            @ApiParam("语言012 中英日(非必填)") @RequestParam(required = false) Integer language
    ) throws Exception {
        language = language == null ? Constant.CHINESE : language;
        return new ResultObject<>(MessageCode.CODE_SUCCESS, language, jobOffersService.frontendList(typeId, language));
    }

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    @ApiOperation(value = "发送简历邮件")
    @ResponseBody
    public ResultObject sendMail(
            HttpServletRequest request,
            @Valid @RequestBody SendResumeMailQo qo,
            BindingResult errors
    ) throws Exception {
        if (errors.hasErrors()) {
            return new ResultObject<>(MessageCode.CODE_PARAMETER_ERROR, null, errors.getAllErrors());
        }
        qo.setLanguage(qo.getLanguage() == null ? Constant.CHINESE : qo.getLanguage());
        jobOffersService.sendMail(qo);
        return new ResultObject(MessageCode.CODE_SUCCESS, qo.getLanguage());
    }
}
