package site.binghai.SuperDumpling.system;

import lombok.Data;

/**
 * Created by binghai on 2017/9/15.
 *
 * @ MoGuJie
 */
@Data
public class JSONResponse {
    private String code = "success";
    private Object data;
    private String msg;
}
