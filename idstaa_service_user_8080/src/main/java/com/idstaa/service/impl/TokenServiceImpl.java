package com.idstaa.service.impl;

import com.idstaa.dao.TokenDao;
import com.idstaa.pojo.Token;
import com.idstaa.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenjie
 * @date 2021/1/5 2:25
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenDao tokenDao;

    @Override
    public Token save(Token token) {
        return tokenDao.save(token);
    }

    @Override
    public Token findOne(Token token) {
        Example<Token> userExample = Example.of(token);
        List<Token> all = tokenDao.findAll(userExample,new Sort(Sort.Direction.DESC, "id"));
        //需要结果过做判断，查询结果为null时会报NoSuchElementException
        if (all!=null && all.size()>0) {
            return all.get(0);
        }
        return null;
    }
}
