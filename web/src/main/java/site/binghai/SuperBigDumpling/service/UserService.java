package site.binghai.SuperBigDumpling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.dao.UserDao;
import site.binghai.SuperBigDumpling.entity.people.User;

import java.util.List;

/**
 * Created by binghai on 2017/10/28.
 *
 * @ MoGuJie
 */
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public User findByUuid(String uuid){
        List<User> rs = userDao.findByUuid(uuid);
        if(rs.size() > 1){
            logger.error("one uuid for more user! uuid:{}",uuid);
            return rs.get(0);
        }
        return rs.isEmpty() ? null : rs.get(0);
    }

    public User findByOpenId(String openid) {
        List<User> rs = userDao.findByOpenId(openid);
        if(rs.size() > 1){
            logger.error("one uuid for more user! openid:{}",openid);
            return rs.get(0);
        }
        return rs.isEmpty() ? null : rs.get(0);
    }
}
