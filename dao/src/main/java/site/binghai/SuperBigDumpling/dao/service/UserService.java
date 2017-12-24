package site.binghai.SuperBigDumpling.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.people.MemberDataBundle;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.dao.UserDao;

import java.util.List;

/**
 * Created by binghai on 2017/10/28.
 *
 * @ MoGuJie
 */
@Service
public class UserService extends BaseService<User> {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderService orderService;

    public User findByUuid(String uuid) {
        List<User> rs = userDao.findByUuid(uuid);
        if (rs.size() > 1) {
            logger.error("one uuid for more user! uuid:{}", uuid);
            return rs.get(0);
        }
        return rs.isEmpty() ? null : rs.get(0);
    }

    public User findByOpenId(String openid) {
        List<User> rs = userDao.findByOpenId(openid);
        if (rs.size() > 1) {
            logger.error("one uuid for more user! openid:{}", openid);
            return rs.get(0);
        }
        return rs.isEmpty() ? null : rs.get(0);
    }

    @Override
    JpaRepository<User, Integer> getDao() {
        return userDao;
    }

    public MemberDataBundle getMemberData(User user) {
        MemberDataBundle bundle = new MemberDataBundle();

        bundle.setUnpay(orderService.countByUserIdAndStatus(user.getId(), OrderStatusEnum.WAITING_PAY));
        bundle.setUngroup(orderService.countByUserIdAndStatus(user.getId(), OrderStatusEnum.WAITING_GROUP_BUILED));
        bundle.setUnsend(orderService.countByUserIdAndStatus(user.getId(), OrderStatusEnum.GROUP_BUILED) +
                orderService.countByUserIdAndStatus(user.getId(), OrderStatusEnum.SINGEL_ORDER_BUILED));
        bundle.setUnreceipt(orderService.countByUserIdAndStatus(user.getId(), OrderStatusEnum.DISTRIBUTOIN));
        return bundle;
    }
}
