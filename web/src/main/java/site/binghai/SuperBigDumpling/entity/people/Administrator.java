package site.binghai.SuperBigDumpling.entity.people;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IceSea on 2017/9/18.
 * 管理员
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
public class Administrator extends BaseUser{
    @Id
    @GeneratedValue
    private int id;
}
