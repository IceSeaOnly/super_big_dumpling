package site.binghai.SuperBigDumpling.common.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;
import site.binghai.SuperBigDumpling.common.entity.people.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/9/30.
 * 团
 * @ MoGuJie
 */
@Entity(name = "groups")
@Data
public class Group extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private TradeItem tradeItem;
    private User groupMaster; //团长
    private int status;
    private int leftNum; // 剩余人数
    private int leftTime; //剩余时间,秒
    private int totalNum; //总人数
}
