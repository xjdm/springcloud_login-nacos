package com.idstaa.service.impl;

import com.idstaa.dao.AuthCodeDao;
import com.idstaa.dao.UserDao;
import com.idstaa.pojo.AuthCode;
import com.idstaa.pojo.User;
import com.idstaa.service.AuthCodeService;
import com.idstaa.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    public User saveUser(User user){
        User save = userDao.save(user);
        return save;
    }

    public User findOne(User authCode){
        Example<User> userExample = Example.of(authCode);
        List<User> all = userDao.findAll(userExample,new Sort(Sort.Direction.DESC, "id"));
        //需要结果过做判断，查询结果为null时会报NoSuchElementException
        if (all!=null && all.size()>0) {
            return all.get(0);
        }
        return null;
    }

}
