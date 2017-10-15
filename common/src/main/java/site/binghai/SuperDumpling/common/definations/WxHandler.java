package site.binghai.SuperDumpling.common.definations;

import java.util.Map;

/**
 * Created by binghai on 2017/10/15.
 *
 * @ MoGuJie
 */
public interface WxHandler {
    /**
     * 传入请求，由具体处理器返回结果
     * */
    Object handleRequest(Map params);
    /**
     * 上层调用处理器获取act
     * */
    String getActHeader();
}
