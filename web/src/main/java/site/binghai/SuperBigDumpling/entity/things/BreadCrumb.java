package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IceSea on 2017/9/18.
 * GitHub: https://github.com/IceSeaOnly
 */
@Entity
@Data
public class BreadCrumb extends AbstractEntity{
    @Id
    @GeneratedValue
    private int id;
    private String content;
}
