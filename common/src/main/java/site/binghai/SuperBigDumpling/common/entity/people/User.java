package site.binghai.SuperBigDumpling.common.entity.people;

import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.BaseUser;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;

import javax.persistence.*;
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
    private String avatarUrl;
    private String password;
    private String phone;
    private int cps;//积分
    private int money; //余额，单位分
    private String openId;
    private String uuid;
    private String sessionKey;
    @ElementCollection
    private List<Integer> collections; //收藏的商品id
}
