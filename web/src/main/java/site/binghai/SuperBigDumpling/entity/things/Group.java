package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.people.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/9/30.
 * 团
 * @ MoGuJie
 */
@Entity
@Data
public class Group extends AbstractEntity{
    @Id
    @GeneratedValue
    private int id;
    private TradeItem tradeItem;
    private User groupMaster; //团长
    private int status;
}
