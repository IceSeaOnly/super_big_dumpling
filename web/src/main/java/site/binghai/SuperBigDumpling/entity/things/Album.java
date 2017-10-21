package site.binghai.SuperBigDumpling.entity.things;

import lombok.Data;
import site.binghai.SuperBigDumpling.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IceSea on 2017/10/21.
 * 详情页图片
 * GitHub: https://github.com/IceSeaOnly
 */
@Data
@Entity
public class Album extends AbstractEntity implements Comparable<Album>{
    @Id
    @GeneratedValue
    private int id;
    private int weight;
    private String url;

    @Override
    public int compareTo(Album o) {
        return 0;
    }
}
