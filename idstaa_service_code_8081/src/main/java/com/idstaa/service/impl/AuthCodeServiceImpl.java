package com.idstaa.service.impl;

import com.idstaa.dao.AuthCodeDao;
import com.idstaa.pojo.AuthCode;
import com.idstaa.service.AuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjie
 * @date 2021/1/4 15:01
 */
@Service
public class AuthCodeServiceImpl implements AuthCodeService {
    @Autowired
    private AuthCodeDao authCodeDao;
    public AuthCode saveAuthCode(AuthCode authCode){
        AuthCode save = authCodeDao.save(authCode);
        return save;
    }

    public AuthCode findOne(AuthCode authCode){
        Example<AuthCode> userExample = Example.of(authCode);
        List<AuthCode> all = authCodeDao.findAll(userExample,new Sort(Sort.Direction.DESC, "id"));
        //需要结果过做判断，查询结果为null时会报NoSuchElementException
        if (all!=null && all.size()>0) {
            return all.get(0);
        }
        return null;
    }

}
