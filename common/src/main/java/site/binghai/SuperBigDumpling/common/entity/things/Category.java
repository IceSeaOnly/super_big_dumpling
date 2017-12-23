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
public class Category extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    private Category fatherCategory; // 父类目
    private String cname;
    private String imgUrl;
    private String categoryDesc;
    public boolean isSuperCategory(){
        return fatherCategory == null;
    }
}
