package com.vsc.website.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.vsc.website.common.Constant;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.ResultObject;
import com.vsc.website.service.DictService;
import com.vsc.website.service.UserService;
import com.vsc.website.vo.common.CDictVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = {"共通-下拉接口"})
@RestController
@RequestMapping("/commons/dict")
public class CDictController {

    @Resource
    private DictService dictService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/pullDown", method = RequestMethod.GET)
    @ApiOperation(value = "字典下拉")
    @ResponseBody
    public ResultObject<List<CDictVo>> pullDown(
            HttpServletRequest request,
            @ApiParam("下拉参数 行业:industry 招聘类型:type 所属部门:department 工作地点:address  方案类型:kind") @RequestParam String type,
            @ApiParam("语言 012 中英日") @RequestParam(required = false) Integer language
    )throws Exception{
        language=language==null? Constant.CHINESE : language;
        return new ResultObject<>(MessageCode.CODE_SUCCESS,language,dictService.getDict(type,language));
    }

    @RequestMapping(value = "/getIpInfo", method = RequestMethod.GET)
    @ApiOperation(value = "ip信息")
    @ResponseBody
    public ResultObject<JSONObject> getIpInfo(
            HttpServletRequest request
    )throws Exception{
        return new ResultObject<>(MessageCode.CODE_SUCCESS,Constant.CHINESE,userService.getIpInfo(request.getRemoteAddr()));
    }
}
