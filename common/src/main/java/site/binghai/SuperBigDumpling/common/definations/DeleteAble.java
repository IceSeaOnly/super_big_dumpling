package site.binghai.SuperBigDumpling.common.definations;

/**
 * Created by IceSea on 2017/9/19.
 * GitHub: https://github.com/IceSeaOnly
 */
public interface DeleteAble {
    boolean hasDelete(); //是否被删除
    boolean accessAble(); //是否可用
    void delete(); // 删除
    void unAvailable(); // 置为不可用
    int getId();
}
