package site.binghai.SuperBigDumpling.common.entity.things;

import lombok.Builder;
import lombok.Data;
import site.binghai.SuperBigDumpling.api.enums.GroupStatusEnum;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.people.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by binghai on 2017/9/30.
 * 团
 *
 * @ MoGuJie
 */
@Entity(name = "groups")
@Data
public class Group extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private TradeItem tradeItem;
    @OneToOne
    private User groupMaster; //团长
    private GroupStatusEnum status;
    private String groupStatus;
    private int leftNum; // 剩余人数
    private int leftTime; //剩余时间,秒
    private int totalNum; //总人数
    @ElementCollection
    private List<Integer> orders;

    public void setStatus(GroupStatusEnum status) {
        this.status = status;
        this.groupStatus = status.getGroupStatus();
    }
}
