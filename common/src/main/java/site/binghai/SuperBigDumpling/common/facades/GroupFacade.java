package site.binghai.SuperBigDumpling.common.facades;

import com.alibaba.fastjson.JSONObject;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;

/**
 * Created by binghai on 2017/12/17.
 *
 * @ super_big_dumpling
 */
public class GroupFacade {
    private int gid; //团id
    private String groupStatus;
    private boolean isSelf;
    private String img; //商品图
    private String name; //商品名称
    private int groupNum; // n人团
    private int saleNum; // 已团
    private String unit; // 单位
    private int gprice; // 拼团价格
    private int leftNum; //此团已有人数
    private int leftTime; //剩余时间
    private JSONObject groupMaster; //内涵avatarUrl团长头衔链接
    private int createTime; // 参团时间
    private TradeItem item;

    public GroupFacade(Group group, User user, TradeItem tradeItem) {
        this.gid = group.getId();
        this.groupStatus = group.getGroupStatus();
        this.isSelf = true; //todo 此处要判断是否本人意见参此团
        this.img = tradeItem.getImgUrl();
        this.name = tradeItem.getName();
        this.groupNum = tradeItem.getGroupSize();
        this.saleNum = tradeItem.getSaleNum();
        this.unit = tradeItem.getUnit();
        this.gprice = tradeItem.getPrice();
        this.leftNum = group.getOrders().size();
        this.leftTime = group.getLeftTime();
        this.groupMaster = new JSONObject();
        this.groupMaster.put("avatarUrl",group.getGroupMaster().getAvatarUrl());
        this.createTime = new Long(group.getCreated()/1000).intValue();
        this.item = tradeItem;
    }
}
