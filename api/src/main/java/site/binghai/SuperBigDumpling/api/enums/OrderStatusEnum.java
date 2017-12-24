package site.binghai.SuperBigDumpling.api.enums;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by binghai on 2017/9/30.
 *
 * @ MoGuJie
 */
public enum OrderStatusEnum {
    WAITING_PAY(0,"待支付"),
    WAITING_GROUP_BUILED(1,"已支付待成团"),
    GROUP_BUILED(2,"已成团待发货"),
    DISTRIBUTOIN(3,"配送中"),
    COMPLETED(4,"已完成"),
    REFUNDING(5,"退款中"),
    CANCLED(6,"已取消"),
    WAITING_RETURN_GOODS(7,"待退货"),
    RE_DISTRIBUTOIN(8,"重新配送中"),
    REQ_CANCLE(9,"申请退款中"),
    GROUP_FAILED(10,"拼团失败"),
    REFUNDED(11,"已退款"),
    SINGEL_ORDER_BUILED(12,"已支付待发货"), // 单购支付成功
    ;

    private int index;
    private String status;

    private static HashMap<Integer,OrderStatusEnum> all = new HashMap<>();
    static {
        Arrays.stream(OrderStatusEnum.values()).forEach(v -> all.put(v.getIndex(),v));
    }

    public static OrderStatusEnum of(int index){
        return all.get(index);
    }

    OrderStatusEnum(int index, String status) {
        this.index = index;
        this.status = status;
    }

    public int getIndex() {
        return index;
    }

    public String getStatus() {
        return status;
    }
}
