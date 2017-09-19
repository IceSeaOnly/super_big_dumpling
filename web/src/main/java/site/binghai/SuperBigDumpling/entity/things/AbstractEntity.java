package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import site.binghai.SuperDumpling.common.definations.DeleteAble;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@MappedSuperclass
@Data
public class AbstractEntity implements Serializable,DeleteAble {
    protected long created = System.currentTimeMillis();
    protected long updated = System.currentTimeMillis();
    protected boolean isDeleted = false;
    protected boolean available = true;
    protected int userId;
    protected String owner;

    @Override
    public boolean hasDelete() {
        return isDeleted();
    }

    @Override
    public boolean accessAble() {
        return isAvailable();
    }

    @Override
    public void delete() {
        setDeleted(true);
    }

    @Override
    public void unAvailable() {
        setAvailable(false);
    }
}
