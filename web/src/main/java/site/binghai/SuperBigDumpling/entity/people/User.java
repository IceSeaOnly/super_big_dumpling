package site.binghai.SuperBigDumpling.entity.people;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.BaseUser;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by binghai on 2017/9/30.
 *
 * @ MoGuJie
 */
@Data
@Entity
public class User extends BaseUser {
    @Id
    @GeneratedValue
    private int id;
    private String password;
    private String phone;
    private int cps;//积分
    private int money; //余额，单位分
    @ElementCollection
    private List<TradeItem> collections; //收藏
}
