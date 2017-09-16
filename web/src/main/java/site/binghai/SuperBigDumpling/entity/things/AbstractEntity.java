package site.binghai.SuperBigDumpling.entity.things;

import javax.persistence.MappedSuperclass;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@MappedSuperclass
public class AbstractEntity {
    private long created = System.currentTimeMillis();
    private long updated = System.currentTimeMillis();
    private boolean isDeleted = false;
    private boolean available = true;
    private int userId;
}
