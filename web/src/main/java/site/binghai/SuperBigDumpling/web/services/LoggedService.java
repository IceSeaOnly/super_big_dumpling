package site.binghai.SuperBigDumpling.web.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by binghai on 2017/12/24.
 *
 * @ super_big_dumpling
 */
public abstract class LoggedService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
