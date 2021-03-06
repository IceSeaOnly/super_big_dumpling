package site.binghai.SuperBigDumpling.common.facades;

import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.things.Category;

/**
 * Created by binghai on 2017/10/15.
 *
 * @ MoGuJie
 */
@Data
public class CategoryFacade extends BaseFacade<Category> {
    private int id;
    private String name;
    private String img;

    public CategoryFacade() {
    }

    private CategoryFacade(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    @Override
    public BaseFacade asObj(Category obj) {
        return new CategoryFacade(obj.getId(),obj.getCname(),obj.getImgUrl());
    }
}
