package site.binghai.SuperBigDumpling.facades;

import lombok.Builder;
import lombok.Data;
import site.binghai.SuperBigDumpling.entity.things.Album;
import site.binghai.SuperBigDumpling.entity.things.Group;
import site.binghai.SuperBigDumpling.entity.things.Property;
import site.binghai.SuperBigDumpling.entity.things.TradeItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by binghai on 2017/10/15.
 *
 * @ MoGuJie
 */
@Data
@Builder
public class TradeItemFacade extends BaseFacade<TradeItem> {
    private int id;
    private String img;
    private String name;
    private double gprice;
    private double mprice;
    private double price;
    private int saleNum;
    private String unit;
    private List<String> album;
    private String intro;
    private List<Property> property;

    // 以下为拼装数据
    private int groupNum;
    private boolean is_collect;
    private List<Group> groupList;

    public TradeItemFacade() {
    }

    public TradeItemFacade(int id, String img, String name, double gprice, double mprice, double price, int saleNum, String unit, List<String> album, String intro, List<Property> property, int groupNum, boolean is_collect, List<Group> groupList) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.gprice = gprice;
        this.mprice = mprice;
        this.price = price;
        this.saleNum = saleNum;
        this.unit = unit;
        this.album = album;
        this.intro = intro;
        this.property = property;
        this.groupNum = groupNum;
        this.is_collect = is_collect;
        this.groupList = groupList;
    }

    @Override
    public TradeItemFacade asObj(TradeItem obj) {
        return TradeItemFacade.builder()
                .id(obj.getId())
                .img(obj.getImgUrl())
                .name(obj.getName())
                .gprice(obj.getPrice() / 100.0)
                .mprice(obj.getMprice() / 100.0)
                .price(obj.getOprice() / 100.0)
                .saleNum(obj.getSaleNum())
                .unit(obj.getUnit())
                .album(album2String(obj.getAlbum()))
                .intro(obj.getDetailDesp())
                .property(obj.getProperties())

                .groupNum(0)
                .is_collect(false)
                .groupList(Collections.EMPTY_LIST)
                .build();
    }

    private List<String> album2String(List<Album> album) {
        List<String> rs = new ArrayList<>();
        Collections.sort(album);
        album.forEach(v -> rs.add(v.getUrl()));
        return rs;
    }
}
