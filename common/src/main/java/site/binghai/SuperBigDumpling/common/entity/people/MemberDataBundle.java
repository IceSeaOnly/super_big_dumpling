package site.binghai.SuperBigDumpling.common.entity.people;

import lombok.Data;

/**
 * Created by binghai on 2017/12/24.
 * 用户中心的数据bundle
 * @ super_big_dumpling
 */
@Data
public class MemberDataBundle {
    private long unpay; // 待支付
    private long ungroup; // 待成团
    private long unsend; // 待发货
    private long unreceipt; // 待收货
}
