package site.binghai.SuperBigDumpling.web.service;

import org.springframework.data.jpa.repository.JpaRepository;
import site.binghai.SuperBigDumpling.common.definations.DeleteAble;

import javax.transaction.Transactional;

/**
 * Created by binghai on 2017/12/2.
 *
 * @ super_big_dumpling
 */
public abstract class BaseService<T extends DeleteAble> {
    abstract JpaRepository<T, Integer> getDao();

    @Transactional
    public T save(T t) {
        return getDao().save(t);
    }

    /**
     * 更新不存在的记录会失败
     * */
    @Transactional
    public T update(T t) {
        if (t.getId() > 0) {
            return save(t);
        }
        return t;
    }

    public T findById(Integer id){
        return getDao().findOne(id);
    }
}
