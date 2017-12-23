package site.binghai.SuperBigDumpling.common.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IceSea on 2017/10/22.
 * 商品可选属性
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
public class Property extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> value;
}
