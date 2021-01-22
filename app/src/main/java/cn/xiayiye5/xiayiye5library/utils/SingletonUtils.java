package cn.xiayiye5.xiayiye5library.utils;

/**
 * 单利类工具类
 *
 * @author soyoungboy
 */
public abstract class SingletonUtils<T> {
    private T instance;


    protected abstract T newInstance();


    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
