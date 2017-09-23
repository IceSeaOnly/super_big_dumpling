package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    @OneToOne
    private Category fatherCategory; // 父类目
    private String cname;
    private String imgUrl;
    private String categoryDesc;
    public boolean isSuperCategory(){
        return fatherCategory == null;
    }
}
