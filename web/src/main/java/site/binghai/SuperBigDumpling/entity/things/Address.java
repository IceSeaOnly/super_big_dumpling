package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/10/8.
 * 用来选择的地址
 * @ MoGuJie
 */
@Entity
@Data
public class Address extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private Address superAddress; //上级地址
    private String name; //详细
}
