package site.binghai.SuperBigDumpling.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.api.enums.OrderStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.common.utils.UserUtils;
import site.binghai.SuperBigDumpling.dao.GroupDao;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
@Service
public class GroupService extends BaseService<Group> {
    @Autowired
    private GroupDao dao;

    public List<Group> findByTradeItemIdAndStatus(Integer tradeItemId, GroupStatusEnum status, int page) {
        return dao.findByTradeItemIdAndStatusAndAvailable(tradeItemId, status, true, new PageRequest(page, 10));
    }

    /**
     * 新开团
     */
    @Transactional
    public Group newGroup(TradeItem tradeItem, User user) {
        Group group = new Group();
        group.setTotalNum(tradeItem.getGroupSize());
        group.setLeftNum(tradeItem.getGroupSize() - 1);
        group.setGroupMaster(user);
        group.setStatus(GroupStatusEnum.GROUPING);
        group.setOutOfTime(System.currentTimeMillis() + tradeItem.getGroupMaxTime() * 1000);
        group.setTradeItemId(tradeItem.getId());
        UserUtils.userInit(group, user);
        return dao.save(group);
    }

    @Override
    JpaRepository<Group, Integer> getDao() {
        return dao;
    }

    /**
     * 查询所有未成团的团
     */
    public List<Group> findAllNotBuildGroup() {
        List<Group> groups = new ArrayList<>();
        int page = 0;
        int size = 0;
        do {
            size = groups.size();
            groups.addAll(dao.findByStatusAndAvailable(GroupStatusEnum.GROUPING, true, new PageRequest(page++, 100)));
        } while (groups.size() > size);
        return groups;
    }
}
