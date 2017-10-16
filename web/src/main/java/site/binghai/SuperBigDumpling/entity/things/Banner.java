package site.binghai.SuperBigDumpling.entity.things;

import com.sun.istack.internal.Nullable;
import lombok.Data;
import site.binghai.SuperBigDumpling.entity.AbstractEntity;

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
    @Nullable
    private Integer typeVal; //跳转类型
}
