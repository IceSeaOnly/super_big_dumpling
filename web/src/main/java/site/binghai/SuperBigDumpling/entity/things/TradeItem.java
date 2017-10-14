package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "类目信息不正确")
    private Category fatherCategory; // 父类目
    @OneToOne
    @NotNull(message = "类目信息不正确")
    private Category childCategory; // 子类目
    @NonNull
    @NotEmpty(message = "名称不能为空")
    private String name;
    @NonNull
    @NotEmpty(message = "描述不能为空")
    private String desp; // 一句话描述
    @NonNull
    @NotEmpty(message = "图片链接不能为空")
    private String imgUrl;
    @Min(value = 0,message = "价格不能小于0")
    private int price; // 团购价
    @Min(value = 0,message = "价格不能小于0")
    private int oprice; // 原价
    @Column(columnDefinition = "TEXT")
    @Lob
    private String detailDesp; //富文本内容

    public TradeItem() {
    }
}
