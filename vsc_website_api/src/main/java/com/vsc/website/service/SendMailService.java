package com.vsc.website.service;

import com.vsc.website.common.Util;
import com.vsc.website.entity.Attachment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class SendMailService {
    @Resource
    private JavaMailSender mailSender;

    @Value("${config.attachFolder}")
    private String attachFolder;

    /**
     * 发送邮件
     * @throws Exception
     */
    @Async
    public void sendMail(String subject,String text,Attachment attachment) throws MessagingException, IOException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(Util.getConfig("spring.mail.username"));
        helper.setTo(Util.getConfig("receiving.mailbox"));
        helper.setSubject(subject);
        helper.setText(text);
        FileSystemResource file = new FileSystemResource(new File(attachFolder+attachment.getFilename()));
        helper.addAttachment(attachment.getFilenameOriginal(), file);
        mailSender.send(mimeMessage);
    }
}
