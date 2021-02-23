package cn.xiayiye5.xiayiye5library.observer;

/**
 * @author : xiayiye5
 * @date : 2021/2/23 15:18
 * 类描述 :
 */
public interface XYYObserverable {
    void register(XYYObserver o);

    void remove(XYYObserver o);

    void notifyData();
}
