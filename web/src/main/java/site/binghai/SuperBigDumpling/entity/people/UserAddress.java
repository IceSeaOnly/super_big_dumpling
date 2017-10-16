package site.binghai.SuperBigDumpling.entity.people;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/10/16.
 * 用户常用地址
 * @ MoGuJie
 */
@Data
@Entity
public class UserAddress extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    private String address;
    private String phone;
    private String detailInfo;
}
