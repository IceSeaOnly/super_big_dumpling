package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by IceSea on 2017/9/25.
 * 商品
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
public class TradeItem extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Category fatherCategory; // 父类目
    @OneToOne
    private Category childCategory; // 子类目
    private String name;
    private String desp; // 一句话描述
    private String imgUrl;
    private int price; // 团购价
    private int oprice; // 原价
    private String detailDesp; //富文本内容
}
