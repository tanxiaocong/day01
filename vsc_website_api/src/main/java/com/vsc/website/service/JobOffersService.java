package com.vsc.website.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vsc.website.common.ApiException;
import com.vsc.website.common.MessageCode;
import com.vsc.website.common.PageBean;
import com.vsc.website.dao.JobOffersMapper;
import com.vsc.website.entity.Attachment;
import com.vsc.website.entity.JobOffers;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.qo.backend.BJobOffersAddQo;
import com.vsc.website.qo.frontend.SendResumeMailQo;
import com.vsc.website.vo.backend.BJobOffersDetailVo;
import com.vsc.website.vo.backend.BJobOffersListVo;
import com.vsc.website.vo.backend.BJobOffersNamesVo;
import com.vsc.website.vo.backend.BProgramListVo;
import com.vsc.website.vo.frontend.FJobOffersListVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class JobOffersService {
    @Resource
    private JobOffersMapper jobOffersMapper;
    @Resource
    private JobOffersNamesService jobOffersNamesService;
    @Resource
    private DictService dictService;
    @Resource
    private AttachmentService attachmentService;
    @Resource
    private SendMailService sendMailService;

    /**
     * 添加
     *
     * @param qo
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void add(BJobOffersAddQo qo, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        dictService.sureDict(qo.getTypeId(), loginTokens.getLanguage());
        dictService.sureDict(qo.getAddressId(), loginTokens.getLanguage());
        dictService.sureDict(qo.getDepartmentId(), loginTokens.getLanguage());

        JobOffers jobOffers = new JobOffers();
        PropertyUtils.copyProperties(jobOffers, qo);
        jobOffers.setUpdateAt(new Date());
        jobOffers.setUpdateUser(loginTokens.getUserId());
        jobOffers.setCreateAt(new Date());
        jobOffers.setCreateUser(loginTokens.getUserId());
        jobOffersMapper.insert(jobOffers);
        jobOffersNamesService.add(qo, jobOffers.getId());
    }

    /**
     * 更新招聘岗位
     *
     * @param qo
     * @param id
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void update(BJobOffersAddQo qo, Integer id, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        JobOffers jobOffers = sureJobOffers(id, loginTokens.getLanguage());
        dictService.sureDict(qo.getTypeId(), loginTokens.getLanguage());
        dictService.sureDict(qo.getAddressId(), loginTokens.getLanguage());
        dictService.sureDict(qo.getDepartmentId(), loginTokens.getLanguage());

        PropertyUtils.copyProperties(jobOffers, qo);
        jobOffers.setUpdateUser(loginTokens.getUserId());
        jobOffers.setUpdateAt(new Date());
        jobOffersMapper.updateByPrimaryKeySelective(jobOffers);
        jobOffersNamesService.add(qo, id);
    }

    /**
     * 列表
     *
     * @param keywords
     * @param loginTokens
     * @param page
     * @param size
     * @return
     */
    public PageBean<BJobOffersListVo> list(String keywords, LoginTokens loginTokens, int page, int size) {
        PageHelper.startPage(page, size);
        List<BJobOffersListVo> vos = jobOffersMapper.list(keywords, loginTokens.getLanguage());
        PageInfo<BProgramListVo> pageInfo = new PageInfo(vos);
        return new PageBean<>(page, size, pageInfo.getTotal(), vos);
    }

    /**
     * 前台列表
     *
     * @param typeId
     * @param language
     * @return
     */
    public List<FJobOffersListVo> frontendList(Integer typeId, Integer language) {
        List<FJobOffersListVo> vos = jobOffersMapper.frontendList(typeId, language);
        for (FJobOffersListVo vo : vos) {
            vo.setJobOffersNamesVos(jobOffersNamesService.detail(vo.getId()));
        }
        return vos;
    }

    /**
     * 详情
     *
     * @param id
     * @param language
     * @return
     */
    public BJobOffersDetailVo detail(Integer id, Integer language) {
        sureJobOffers(id, language);
        BJobOffersDetailVo vo = jobOffersMapper.detail(id, language);
        vo.setJobOffersNamesVos(jobOffersNamesService.detail(id));
        return vo;
    }

    /**
     * 删除
     *
     * @param id
     * @param loginTokens
     */
    public void delete(Integer id, LoginTokens loginTokens) {
        sureJobOffers(id, loginTokens.getLanguage());
        jobOffersMapper.deleteByPrimaryKey(id);
        jobOffersNamesService.delete(id);
    }

    /**
     * 确认岗位
     *
     * @param jobOffersId
     * @param language
     * @return
     */
    public JobOffers sureJobOffers(Integer jobOffersId, Integer language) {
        JobOffers jobOffers = jobOffersMapper.selectByPrimaryKey(jobOffersId);
        if (jobOffers == null) {
            throw new ApiException(MessageCode.CODE_NO_JOB_OFFERS, language);
        }
        return jobOffers;
    }

    /**
     * 发送简历
     *
     * @param qo
     * @throws IOException
     * @throws MessagingException
     */
    public void sendMail(SendResumeMailQo qo) throws IOException, MessagingException {
        Attachment attachment = attachmentService.getAttachmentByFileName(qo.getFileName());
        BJobOffersDetailVo vo = detail(qo.getJobOffersId(), qo.getLanguage());
        for (BJobOffersNamesVo namesVo : vo.getJobOffersNamesVos()) {
            if (namesVo.getLanguage().equals(qo.getLanguage())) ;
            sendMailService.sendMail("应聘-"+namesVo.getJobName()+"-【"+attachment.getFilenameOriginal()+"】","【"+vo.getTypeName()+"-"+namesVo.getJobName()+"】 有人投递简历，请查看。", attachment);
            break;
        }
    }
}
