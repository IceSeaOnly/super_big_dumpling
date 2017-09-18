package site.binghai.SuperBigDumpling.entity.things;

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
public class AbstractEntity implements Serializable{
    protected long created = System.currentTimeMillis();
    protected long updated = System.currentTimeMillis();
    protected boolean isDeleted = false;
    protected boolean available = true;
    protected int userId;
    protected String owner;
}
