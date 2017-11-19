package site.binghai.SuperBigDumpling.common.utils;

/**
 * Created by binghai on 2017/9/9.
 *
 * @ MoGuJie
 */
public class SimpleLogger {
    public static void log(String... params) {
        for (String p : params) {
            log(p);
        }
    }

    public static void log(String param) {
        System.out.println(TimeFormatter.format(System.currentTimeMillis()) + " : " + param);
    }
}
