package site.binghai.SuperDumpling.common.system;

import lombok.Data;

/**
 * Created by IceSea on 2017/9/25.
 * GitHub: https://github.com/IceSeaOnly
 */
public enum  ErrorList {
    INVALID_PARAMETER(4001,"参数非法",null),
    NON_EMPTY_SUPER_CATEGORY(4002,"此父类尚有子类关联",null),
    NULL_ACT(4003,"act参数不能为空",null),
    UNKONW_ACT(4004,"act参数无法识别",null),
    ;
    private int errorId;
    private String errorMsg;
    private Object extra;

    ErrorList(int errorId, String errorMsg, Object extra) {
        this.errorId = errorId;
        this.errorMsg = errorMsg;
        this.extra = extra;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
