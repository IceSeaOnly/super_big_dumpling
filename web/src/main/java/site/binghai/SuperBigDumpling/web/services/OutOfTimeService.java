package site.binghai.SuperBigDumpling.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.dao.service.GroupService;
import site.binghai.SuperBigDumpling.dao.service.OrderService;

import java.util.List;

/**
 * Created by binghai on 2017/12/24.
 * 超时服务
 *
 * @ super_big_dumpling
 */
@Service
public class OutOfTimeService extends LoggedService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GroupService groupService;

    /**
     * 成团超时中心
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void GroupBuildOutOfTime() {
        logger.debug("成团超时中心扫描中...");
        List<Group> notBuildGroups = groupService.findAllNotBuildGroup();
        for (Group group : notBuildGroups) {
            if (group.getOutOfTime() <= System.currentTimeMillis()) {
                logger.warn("拼团超时 - 拼团失败 - groupId:{}", group.getId());
                // 超时拼团失败 todo 退款、通知
                group.setStatus(GroupStatusEnum.GROUP_FAILED);
                orderService.groupFailed(group);
                group.setStatus(GroupStatusEnum.GROUP_FAILED);
                groupService.update(group);
            }
        }
    }
}
