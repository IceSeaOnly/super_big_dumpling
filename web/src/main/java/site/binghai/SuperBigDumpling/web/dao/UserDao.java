package site.binghai.SuperBigDumpling.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.entity.people.User;

import java.util.List;

/**
 * Created by binghai on 2017/10/28.
 *
 * @ MoGuJie
 */
public interface UserDao extends JpaRepository<User,Integer>{
    List<User> findByUuid(String uuid);
    List<User> findByOpenId(String openid);
}
