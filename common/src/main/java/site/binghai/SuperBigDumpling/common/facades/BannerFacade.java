package site.binghai.SuperBigDumpling.common.facades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.AbstractEntity;
import site.binghai.SuperBigDumpling.common.entity.things.Banners;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by binghai on 2017/10/16.
 *
 * @ MoGuJie
 */
@Data
@Builder
@AllArgsConstructor
public class BannerFacade extends BaseFacade<Banners>{
    private int id;
    private String img;
    private Integer typeVal; //跳转类型

    @Override
    public BannerFacade asObj(Banners obj) {
        return BannerFacade.builder()
                .id(obj.getId())
                .img(obj.getImage().getImg())
                .typeVal(obj.getCategory().getId())
                .build();
    }

    public BannerFacade() {
    }
}
