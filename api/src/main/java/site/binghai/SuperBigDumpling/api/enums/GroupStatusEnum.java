package site.binghai.SuperBigDumpling.api.enums;

/**
 * Created by binghai on 2017/12/2.
 *
 * @ super_big_dumpling
 */
public enum GroupStatusEnum {
    GROUPING("拼团中"),
    GROUP_SUCCESS("拼团成功"),
    GROUP_FAILED("拼团失败"),
    ;

    private String groupStatus;

    GroupStatusEnum(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getGroupStatus() {
        return groupStatus;
    }
}
