package site.binghai.SuperBigDumpling.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.common.utils.UserUtils;
import site.binghai.SuperBigDumpling.web.dao.GroupDao;

import javax.transaction.Transactional;
import java.util.Collection;
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

    public List<Group> findByTradeItemIdAndStatus(TradeItem item, GroupStatusEnum status, int page) {
        return dao.findByTradeItemAndStatus(item, status, new PageRequest(page, 10));
    }

    /**
     * 新开团
     */
    @Transactional
    public Group newGroup(TradeItem tradeItem, User user) {
        Group group = Group.builder()
                .totalNum(tradeItem.getGroupSize())
                .leftNum(tradeItem.getGroupSize() - 1)
                .groupMaster(user)
                .status(GroupStatusEnum.GROUPING)
                .leftTime(tradeItem.getGroupMaxTime())
                .tradeItem(tradeItem)
                .build();
        UserUtils.userInit(group,user);
        return dao.save(group);
    }

    @Override
    JpaRepository<Group, Integer> getDao() {
        return dao;
    }
}
