package site.binghai.SuperBigDumpling.dao.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.binghai.SuperBigDumpling.common.definations.DeleteAble;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IceSea on 2017/9/18.
 * GitHub: https://github.com/IceSeaOnly
 */
@Service
public class SimpleDataService{
    private static ConcurrentHashMap<Class,SimpleJpaRepository> cache = new ConcurrentHashMap<>();

    @Autowired
    private EntityManager entityManager;

    /**
     * 条件单查询
     * */
    public <T>T findByIdWithConditions(Serializable id,Class<T> clazz,boolean available,boolean isDeleted){
        SimpleJpaRepository repo = getSimpleJpaRepository(clazz);
        Object obj = repo.findOne(id);
        if(obj == null){
            return null;
        }
        DeleteAble deleteAble = (DeleteAble) obj;
        if(available && !deleteAble.accessAble()){
            return null;
        }
        if(isDeleted && deleteAble.hasDelete()){
            return null;
        }
        return (T) deleteAble;
    }

    public <T>T findById(Serializable id,Class<T> clazz){
        return findByIdWithConditions(id,clazz,true,false);
    }

    public List findAll(Class clazz){
        SimpleJpaRepository repo = getSimpleJpaRepository(clazz);
        return repo.findAll((a,b,c)->c.equal(a.get("isDeleted"),false));
    }

    public List findAll(Class clazz,int page, int pageSize){
        SimpleJpaRepository repo = getSimpleJpaRepository(clazz);
        return repo.findAll((a,b,c)-> c.equal(a.get("isDeleted"),false),new PageRequest(page,pageSize)).getContent();
    }

    /**
     * 查询包括已删除的所有记录
     * */
    public List findAllIncludeDeleted(Class clazz){
        SimpleJpaRepository repo = getSimpleJpaRepository(clazz);
        return repo.findAll();
    }

    @Transactional
    public <T>T save(T obj){
        SimpleJpaRepository repo = getSimpleJpaRepository(obj.getClass());
        return (T) repo.save(obj);
    }

    @Transactional
    public void deleteById(Serializable id,Class cz){
        SimpleJpaRepository repo = getSimpleJpaRepository(cz);
        DeleteAble obj = (DeleteAble) repo.findOne(id);
        obj.delete();
        obj.unAvailable();
        repo.flush();
    }

    private <T> SimpleJpaRepository getSimpleJpaRepository(Class<T> clazz) {
        SimpleJpaRepository repo = cache.get(clazz);
        if(repo != null){
            return repo;
        }
        return getRepository(clazz);
    }

    private SimpleJpaRepository getRepository(Class T){
        cache.put(T,new SimpleJpaRepository(T,entityManager));
        return cache.get(T);
    }

    @Transactional
    public void update(Object obj) {
        SimpleJpaRepository repo = getSimpleJpaRepository(obj.getClass());
        repo.saveAndFlush(obj);
    }
}
