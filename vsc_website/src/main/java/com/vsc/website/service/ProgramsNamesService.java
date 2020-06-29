package com.vsc.website.service;

import com.vsc.website.dao.ProgramsNamesMapper;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.entity.ProgramsNames;
import com.vsc.website.qo.backend.BProgramAddQo;
import com.vsc.website.qo.backend.BProgramNamesQo;
import com.vsc.website.vo.backend.BProgramNamesVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ProgramsNamesService {

    @Resource
    private ProgramsNamesMapper programsNamesMapper;

    /**
     * 添加语言版本
     * @param qo
     * @param programId
     * @param loginTokens
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void add(BProgramAddQo qo, Integer programId, LoginTokens loginTokens) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        delete(programId);
        for (BProgramNamesQo programNamesQo: qo.getProgramNamesQos()){
            ProgramsNames programsNames=new ProgramsNames();
            PropertyUtils.copyProperties(programsNames,programNamesQo);
            programsNames.setProgramId(programId);
            programsNames.setCreateAt(new Date());
            programsNames.setCreateUser(loginTokens.getUserId());
            programsNames.setUpdateAt(new Date());
            programsNames.setUpdateUser(loginTokens.getUserId());
            programsNamesMapper.insert(programsNames);
        }
    }

    public List<BProgramNamesVo> getByProgramId(Integer programId){
        List<BProgramNamesVo> vos=programsNamesMapper.getByProgramId(programId);
        return vos;
    }
    /**
     * 删除方案版本
     * @param programId
     */
    public void delete(Integer programId){
        programsNamesMapper.delete(programId);
    }
}
