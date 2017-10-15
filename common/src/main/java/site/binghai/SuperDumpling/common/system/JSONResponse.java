package site.binghai.SuperDumpling.common.system;

import lombok.Data;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@Data
public class JSONResponse {
    private Boolean result;
    private Object data;
    private String msg;
    private String code;

    private JSONResponse(Boolean result, Object data, String msg, String code) {
        this.result = result;
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static JSONResponse errorResp(ErrorList error, String extraMsg, Object data) {
        return new JSONResponse(Boolean.FALSE, data, extraMsg == null ? error.getErrorMsg() : error.getErrorMsg() + ";" + extraMsg, "success");
    }

    public static JSONResponse successResp(String msg, Object data) {
        return new JSONResponse(Boolean.TRUE, data, msg == null ? "" : msg, "success");
    }
}
