package site.binghai.SuperBigDumpling.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public <T>T findById(Serializable id,Class<T> clazz){
        SimpleJpaRepository repo = getSimpleJpaRepository(clazz);
        return (T) repo.findOne(id);
    }

    public List findAll(Class clazz){
        SimpleJpaRepository repo = getSimpleJpaRepository(clazz);
        return repo.findAll();
    }

    @Transactional
    public <T>T save(T obj){
        SimpleJpaRepository repo = getSimpleJpaRepository(obj.getClass());
        return (T) repo.save(obj);
    }

    @Transactional
    public void delete(Object obj){
        SimpleJpaRepository repo = getSimpleJpaRepository(obj.getClass());
        repo.delete(obj);
    }

    @Transactional
    public void deleteById(Serializable id,Class cz){
        SimpleJpaRepository repo = getSimpleJpaRepository(cz);
        repo.delete(id);
    }

    private <T> SimpleJpaRepository getSimpleJpaRepository(Class<T> clazz) {
        SimpleJpaRepository repo = cache.get(clazz);
        if(repo == null){
            repo = getRepository(clazz);
        }
        return repo;
    }

    private SimpleJpaRepository getRepository(Class T){
        cache.put(T,new SimpleJpaRepository(T,entityManager));
        return cache.get(T);
    }
}
