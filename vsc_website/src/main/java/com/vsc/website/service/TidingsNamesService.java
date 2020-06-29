package com.vsc.website.service;

import com.vsc.website.dao.TidingsNamesMapper;
import com.vsc.website.entity.LoginTokens;
import com.vsc.website.entity.TidingsNames;
import com.vsc.website.qo.backend.BTidingsAddQo;
import com.vsc.website.qo.backend.BTidingsNamesQo;
import com.vsc.website.vo.backend.BTidingsNamesVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class TidingsNamesService {

    @Resource
    private TidingsNamesMapper tidingsNamesMapper;

    /**
     * 添加语言版本
     * @param qo
     * @param tidingsId
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public void add(BTidingsAddQo qo, Integer tidingsId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        deleteByTidingsId(tidingsId);
        for (BTidingsNamesQo namesQo:qo.getTidingsNamesQos()) {
            TidingsNames tidingsNames = new TidingsNames();
            tidingsNames.setTidingsId(tidingsId);
            PropertyUtils.copyProperties(tidingsNames,namesQo);
            tidingsNamesMapper.insert(tidingsNames);
        }
    }

    /**
     * 获取新闻内容
     * @param tidingsId
     * @return
     */
    public List<BTidingsNamesVo> getTidingsNames(Integer tidingsId){
        List<BTidingsNamesVo> vos =tidingsNamesMapper.getTidingsNames(tidingsId);
        return vos;
    }

    /**
     * 删除新闻名字
     * @param tidingsId
     */
    public void deleteByTidingsId(Integer tidingsId){
        tidingsNamesMapper.deleteByTidingsId(tidingsId);
    }
}
