package site.binghai.SuperBigDumpling.common.entity.things;

import lombok.Data;

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
public class Express {
    @Id
    @GeneratedValue
    private int id;
    private String type;
    private String sn;
}
