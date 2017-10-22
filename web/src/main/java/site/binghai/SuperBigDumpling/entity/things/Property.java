package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.AbstractEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceSea on 2017/10/22.
 * 商品可选属性
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
public class Property extends AbstractEntity{
    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ElementCollection
    private List<String> value;
}
