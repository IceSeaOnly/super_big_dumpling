package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/10/8.
 *
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
