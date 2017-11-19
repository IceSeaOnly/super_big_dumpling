package site.binghai.SuperBigDumpling.common.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
@Data
@Entity
public class Banner extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private String img;
    private Integer typeVal; //跳转类型
}
