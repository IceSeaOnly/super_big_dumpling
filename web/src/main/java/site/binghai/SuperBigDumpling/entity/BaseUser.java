package site.binghai.SuperBigDumpling.entity;

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
public class BaseUser implements Serializable,DeleteAble {
    protected long created = System.currentTimeMillis();
    protected long lastLogin = System.currentTimeMillis();
    protected boolean isDeleted = false;
    protected boolean forbidden = false;
    protected String username;

    @Override
    public boolean hasDelete() {
        return isDeleted();
    }

    @Override
    public boolean accessAble() {
        return isForbidden();
    }

    @Override
    public void delete() {
        setDeleted(true);
    }

    @Override
    public void unAvailable() {
        setForbidden(true);
    }
}
