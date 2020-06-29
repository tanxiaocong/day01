package com.vsc.website.controller.common;

import com.google.common.io.ByteStreams;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.ResultObject;
import com.vsc.website.common.Util;
import com.vsc.website.config.Config;
import com.vsc.website.entity.Attachment;
import com.vsc.website.service.AttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

@Api(tags = {"共通-附件"})
@RestController
@RequestMapping("/commons/attachments")
public class CAttachmentController {
    @Resource
    private AttachmentService attachmentService;
    @Value("${config.attachFolder}")
    private String attachFolder;
    @Value("${config.fileServer}")
    private String fileServer;

    @ApiOperation(value = "下载附件")
    @RequestMapping(value = "download.do", method = {RequestMethod.GET})
    public void download(
            @ApiParam(value = "附件名称") @RequestParam String filename,
            @ApiParam(value = "保存文件名,导出的文件下载时必传") @RequestParam(required = false) String saveName,
            HttpServletResponse response
    ) throws IllegalStateException, IOException {

        saveName = Util.encodeUriParam(saveName);

        if (!filename.startsWith(Config.tmpFolder)) {
            Attachment attachment = attachmentService.getAttachment(filename);

            response.setContentType(attachment.getMime());
            response.setHeader("Content-Disposition"
                    , "attachment;filename=" + URLEncoder.encode(attachment.getFilenameOriginal(), "UTF-8"));
            ByteStreams.copy(new FileInputStream(attachFolder + attachment.getFilename())
                    , response.getOutputStream());
        } else {
            String mime = Files.probeContentType(Paths.get(attachFolder + filename));
            saveName = StringUtils.isEmpty(saveName) ? filename : saveName;
            response.setContentType(mime);
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(saveName, "UTF-8"));
            ByteStreams.copy(new FileInputStream(attachFolder + filename), response.getOutputStream());
        }
    }

    @ApiOperation(value = "上传临时附件")
    @RequestMapping(value = "uploadTmp", method = {RequestMethod.POST})
    public void uploadTmp(HttpServletRequest request,
                          HttpServletResponse response,
                          @ApiParam(value = "附件") @RequestPart MultipartFile multipartFile
    ) throws IllegalStateException, IOException {
        response.setContentType("text/html");
        String filename = attachmentService.txUploadTmp(multipartFile);
        response.getWriter().println(JSONObject.fromObject(new ResultObject(MessageCode.CODE_SUCCESS,
                null, filename)).toString());
    }

    @ApiOperation(value = "上传富文本图片")
    @RequestMapping(value = "/upload.do", method = {RequestMethod.POST})
    public void upload(HttpServletRequest request,
                       HttpServletResponse response,
                       @ApiParam(value = "附件") @RequestPart MultipartFile multipartFile
    ) throws IllegalStateException, IOException {
        response.setContentType("text/html");
        String filename = attachmentService.txUploadTmp(multipartFile);
        filename = attachmentService.moveTmpFile(filename, Config.attachmentFolder);
        response.getWriter().println(JSONObject.fromObject(new ResultObject<>(MessageCode.CODE_SUCCESS,
                Util.getLoginToken(request).getLanguage(), fileServer + filename)).toString());
    }

}
