package site.binghai.SuperBigDumpling.common.entity.things;
import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;

import javax.persistence.*;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@Entity
@Data
public class Banners extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    private Image image;
    @OneToOne(fetch = FetchType.EAGER)
    private Category category; // 类目
    @OneToOne(fetch = FetchType.EAGER)
    private Region region; // 展示区域
    private int points;// 分数，越大越往前排
    private int status; // 状态，0 for online ,1 for offline
}
