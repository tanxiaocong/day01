package com.vsc.website.controller.common;

import com.vsc.website.service.CaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import nl.captcha.CaptchaBean;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.noise.CurvedLineNoiseProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

@Api(tags={"共通-验证码"})
@RestController
@RequestMapping("/commons/captcha")
public class CCaptchaController {

	@Resource
	private CaptchaService captchaService;

	@ApiOperation(value="获取图片验证码")
	@RequestMapping(value = "getCaptcha.do", method = {RequestMethod.GET}, produces = "image/jpeg")
	@ResponseBody
	public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		CaptchaBean captchaBean = new CaptchaBean(150, 50);
		captchaBean.setBgProd(new FlatColorBackgroundProducer(Color.WHITE));
		captchaBean.setTxtProd(new DefaultTextProducer(4));
		captchaBean.setNoiseProd(new CurvedLineNoiseProducer(Color.BLACK, 5));
		captchaBean = captchaBean.build();
		String captcha = captchaBean.getAnswer();

		request.getSession().setAttribute(CaptchaService.SK_CAPTCHA, captcha);
		response.setHeader("Content-Type", "image/jpeg");
		ImageIO.write(captchaBean.getImage(), "jpg", response.getOutputStream());

//		return new ResultObject(MessageCode.CODE_SUCCESS,null);
	}
	
//	@ApiOperation(value="验证图片验证码")
//	@RequestMapping(value = "verifyCaptcha.do", method = {RequestMethod.GET})
//	@ResponseBody
//	public ResultObject verifyCaptcha(HttpServletRequest request
//			, @ApiParam(value = "验证码") @RequestParam String captcha) {
//		captchaService.checkCaptcha(request, captcha);
//
//		return new ResultObject(MessageCode.CODE_SUCCESS, null);
//	}
}
