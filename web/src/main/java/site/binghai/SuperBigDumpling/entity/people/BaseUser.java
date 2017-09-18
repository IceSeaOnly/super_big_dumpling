package site.binghai.SuperBigDumpling.entity.people;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@MappedSuperclass
@Data
public class BaseUser implements Serializable {
    protected long created = System.currentTimeMillis();
    protected long lastLogin = System.currentTimeMillis();
    protected boolean isDeleted = false;
    protected boolean forbidden = false;
    protected String username;
}
