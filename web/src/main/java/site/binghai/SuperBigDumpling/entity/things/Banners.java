package site.binghai.SuperBigDumpling.entity.things;
import lombok.Data;

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
    @OneToOne
    private Image image;
    @OneToOne
    private Category category; // 类目
    @OneToOne
    private Region region; // 展示区域
}
