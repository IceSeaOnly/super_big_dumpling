package site.binghai.SuperBigDumpling.web.impls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.binghai.SuperBigDumpling.api.services.StockServiceApi;
import site.binghai.SuperBigDumpling.dao.dao.TradeItemDao;

import java.util.concurrent.*;

/**
 * Created by binghai on 2017/11/12.
 * 库存服务
 *
 * @ MoGuJie
 */
@Service
public class StockServiceImpl implements StockServiceApi, InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

    @Autowired
    private TradeItemDao tradeItemDao;
    private ExecutorService executorService; // 单线程执行队列


    @Override
    public int getOneStock(int tradeItemId) {
        Callable<Integer> task = () -> tradeItemDao.consumeOneStock(tradeItemId);
        Future<Integer> future = executorService.submit(task);
        try {
            return future.get();
        } catch (InterruptedException e) {
            logger.error("减库存操作失败-1,tradeItemId:{}", tradeItemId, e);
        } catch (ExecutionException e) {
            logger.error("减库存操作失败-2,tradeItemId:{}", tradeItemId, e);
        }
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        executorService = Executors.newSingleThreadExecutor();
    }
}
