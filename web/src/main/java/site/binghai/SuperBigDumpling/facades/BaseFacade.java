package site.binghai.SuperBigDumpling.facades;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by binghai on 2017/10/15.
 *
 * @ MoGuJie
 */
public abstract class BaseFacade<T> {
    abstract BaseFacade asObj(T obj);

    public List asList(List<T> ls){
        if(ls == null) return Collections.EMPTY_LIST;
        List rs = new ArrayList();
        ls.forEach(v -> rs.add(asObj(v)));
        return rs;
    }
}
