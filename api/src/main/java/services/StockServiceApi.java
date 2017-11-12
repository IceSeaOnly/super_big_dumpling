package services;

/**
 * Created by binghai on 2017/11/12.
 *
 * @ MoGuJie
 */
public interface StockServiceApi {
    /**
     * @param tradeItemId 商品id
     * @return 1 for success，0 for failed (empty stock,etc...)
     * */
    int getOneStock(int tradeItemId);
}
