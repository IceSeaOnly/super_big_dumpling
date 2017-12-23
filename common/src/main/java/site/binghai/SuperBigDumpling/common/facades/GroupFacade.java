package site.binghai.SuperBigDumpling.common.facades;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import site.binghai.SuperBigDumpling.common.entity.people.Order;
import site.binghai.SuperBigDumpling.common.entity.people.User;
import site.binghai.SuperBigDumpling.common.entity.things.Group;
import site.binghai.SuperBigDumpling.common.entity.things.TradeItem;
import site.binghai.SuperBigDumpling.common.utils.TimeFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by binghai on 2017/12/17.
 *
 * @ super_big_dumpling
 */
@Data
public class GroupFacade implements Serializable {
    private int oid; // 团id
    private int gid; //商品id
    private String groupStatus;
    private boolean self; // 本人是否参团
    private String img; //商品图
    private String name; //商品名称
    private int groupNum; // 参团人数
    private int saleNum; // 已团
    private String unit; // 单位
    private double gprice; // 拼团价格
    private int leftNum; //此团已有人数
    private long leftTime; //剩余时间
    private JSONObject groupMaster; //内含avatarUrl团长头衔链接
    private List<Map<String, String>> groupMember; // 参团人员的头像，avatarUrl
    private String createTime; // 参团时间
    private TradeItem item;
    private Object property;// 商品属性
    private Object address; // 拼团成功时显示本人的收货地址

    public GroupFacade(Group group, Order thisUserOrder, List<User> groupMembers, TradeItem tradeItem) {
        this.oid = group.getId();
        this.gid = tradeItem.getId();
        this.groupStatus = group.getGroupStatus();
        this.self = thisUserOrder != null;
        this.img = tradeItem.getImgUrl();
        this.name = tradeItem.getName();
        this.groupNum = tradeItem.getGroupSize();
        this.saleNum = tradeItem.getSaleNum();
        this.unit = tradeItem.getUnit();
        this.gprice = tradeItem.getPrice() / 100.0;
        this.leftNum = group.getOrders().size();
        this.leftTime = (group.getOutOfTime() - System.currentTimeMillis()) / 1000;
        this.leftTime = leftTime > 0 ? leftTime : 0;
        this.groupMaster = new JSONObject();
        this.groupMaster.put("avatarUrl", group.getGroupMaster().getAvatarUrl());
        this.createTime = TimeFormatter.format(group.getCreated());
        this.item = tradeItem;
        this.groupMember = new ArrayList<>();
        this.property = tradeItem.getProperties();
        if (self) {
            address = thisUserOrder.getAddress();
        }

        groupMembers.forEach(v -> {
            Map map = new HashMap<>();
            map.put("avatarUrl", v.getAvatarUrl());
            this.groupMember.add(map);
        });
    }

    public GroupFacade() {
    }
}
