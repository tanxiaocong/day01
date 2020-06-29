package com.vsc.website.service;

import com.vsc.website.common.Constant;
import com.vsc.website.config.Config;
import com.vsc.website.dao.AttachmentMapper;
import com.vsc.website.entity.Attachment;
import org.apache.commons.lang.StringUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class AttachmentService {
    @Resource
    private AttachmentMapper attachmentMapper;
    @Value("${config.attachFolder}")
    private String attachFolder;
    @Value("${config.fileServer}")
    private String fileServer;

    /**
     * 上传临时文件
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    public String txUploadTmp(MultipartFile multipartFile) throws IllegalStateException, IOException {
        String filename = "";

        //保存文件
        filename = saveTmpFile(multipartFile);

        //插入附件表
        addAttachment(filename, multipartFile);

        return filename;
    }

    /**
     * 上传文件
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    public String txUpload(MultipartFile multipartFile, String folder, Integer originalFlag) throws IllegalStateException, IOException {
        String filename = "";

        //保存文件
        filename = saveFile(multipartFile, folder, originalFlag);

        //插入附件表
        addAttachment(filename, multipartFile);

        return filename;
    }


    /**
     * 插入附件表
     *
     * @param filename
     * @param multipartFile
     */
    private void addAttachment(String filename, MultipartFile multipartFile) {
        Attachment attachment = new Attachment();
        attachment.setFilename(filename);
        attachment.setFilenameOriginal(multipartFile.getOriginalFilename());
        attachment.setMime(multipartFile.getContentType());
        attachment.setCreatedAt(new Date());
        attachmentMapper.insert(attachment);
    }

    public void clearTmpFiles() {
        File tmp = new File(attachFolder + Config.tmpFolder);
        File[] files = tmp.listFiles();
        for (File file : files) {
            if (file.lastModified() < System.currentTimeMillis() - Constant.ONE_DAY) {
//                deleteByFileName(Config.tmpFolder+file.getName());
                file.delete();
            }
        }
    }

    public Attachment getAttachment(String filename) {
        return attachmentMapper.selectByFileName(filename);
    }

    /**
     * 生成文件名
     */
    private String genFileName(String filenameOriginal) {
        String extension = filenameOriginal.substring(filenameOriginal.lastIndexOf("."));
        return genFileNameByExt(extension);
    }

    /**
     * 生成文件名
     */
    public String genFileNameByExt(String extension) {
        return UUID.randomUUID().toString() + extension;
    }

    public File getFile(String filename) {
        File file = new File(attachFolder + filename);
        file.getParentFile().mkdirs();

        return file;
    }

    /**
     * 移动临时
     */
    public String moveTmpFile(String filename) throws IOException {
        return moveTmpFile(filename, "");
    }

    /**
     * 移动临时
     */
    public String moveTmpFile(String filename, String destFold) throws IOException {
        if (StringUtils.isEmpty(filename)
                || !filename.startsWith(Config.tmpFolder)) {
            return filename;
        }

        // 创建目录
        new File(attachFolder + destFold).mkdirs();

        String regularFilename = destFold + filename.replace(Config.tmpFolder, "");
        FileUtil.copyFile(new File(attachFolder + filename), new File(attachFolder + regularFilename));
//        boolean flag = new File(attachFolder + filename).renameTo(new File(attachFolder + regularFilename));
        Attachment attachment = getAttachment(filename);
        if (attachment != null) {
            attachment.setFilename(regularFilename);
            attachmentMapper.updateByPrimaryKey(attachment);
        }

        return regularFilename;
    }

    /**
     * 移动临时
     */
    public String moveTmpFileNew(String filename, String destFold, String realName) {
        if (StringUtils.isEmpty(filename)
                || !filename.startsWith(Config.tmpFolder)) {
            return filename;
        }
        // 创建目录
        new File(attachFolder + destFold).mkdirs();
        String regularFilename = destFold + realName;
        // 删除旧文件
        File file = new File(attachFolder + regularFilename);
        if (file.exists() && file.isFile())
            file.delete();

        new File(attachFolder + filename).renameTo(new File(attachFolder + regularFilename));
        Attachment attachment = getAttachment(filename);
        if (attachment != null) {
            attachment.setFilename(regularFilename);
            attachmentMapper.updateByPrimaryKey(attachment);
        }

        return regularFilename;
    }

    /**
     * 保存附件
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    private String saveFile(MultipartFile multipartFile, String folder, Integer originalFlag) throws IllegalStateException, IOException {
        String filenameOriginal = multipartFile.getOriginalFilename();
        String filename = folder + genFileName(filenameOriginal);

        File target = getFile(filename);
        multipartFile.transferTo(target);

        return filename;
    }

    /**
     * 保存附件
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    private String saveTmpFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
        String filenameOriginal = multipartFile.getOriginalFilename();
        String filename = Config.tmpFolder + genFileName(filenameOriginal);

        File target = getFile(filename);

        multipartFile.transferTo(target);

        return filename;
    }

    public String getFileServer() {
        return fileServer;
    }

    /**
     * 改变图像尺寸
     *
     * @throws IOException
     */
    public File resizeImage(File imageFile, String fileName, int width) throws IOException {

        //文件已存在则 直接返回
        File resizedImage = new File(fileServer + width + "_" + fileName);
        if (resizedImage.exists()) {
            return resizedImage;
        } else {
            File path = resizedImage.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
        }

        Image src = Toolkit.getDefaultToolkit().getImage(imageFile.getPath());

        MediaTracker mediaTracker = new MediaTracker(new Container());

        mediaTracker.addImage(src, 0);
        try {
            mediaTracker.waitForID(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BufferedImage image = new BufferedImage(src.getWidth(null), src.getHeight(null), BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(src, 0, 0, null);

        int imageType = BufferedImage.TYPE_INT_RGB;
        if (image.isAlphaPremultiplied()) {
            imageType = BufferedImage.TRANSLUCENT;
        }

        //生成新尺寸图片
        if (image.getWidth() > width) {
            BufferedImage temp = new BufferedImage(width, width * image.getHeight() / image.getWidth(), imageType);
            temp.getGraphics().drawImage(image.getScaledInstance(width, -1, Image.SCALE_SMOOTH),
                    0, 0, null);
            image = temp;

            ImageIO.write(image, "JPEG", resizedImage);
        } else {
            return imageFile;
        }

        return resizedImage;
    }

    public Attachment getAttachmentByFileName(String fileName) {
        return attachmentMapper.selectByFileName(fileName);
    }

    /**
     * 删除数据库
     * @param filename
     */
    public void deleteByFileName(String filename) {
        getAttachmentByFileName(filename);
        attachmentMapper.deleteByFileName(filename);
    }
}
