package site.binghai.SuperBigDumpling.common.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@Entity
@Data
public class Region extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private String regionName;
}
