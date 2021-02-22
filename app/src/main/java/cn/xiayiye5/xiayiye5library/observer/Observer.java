package cn.xiayiye5.xiayiye5library.observer;

/*
 * @author : xiayiye5
 * @date : 2021/2/19 18:22
 * 类描述 :
 */

/***
 * 抽象观察者
 * 定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 * @author jstao
 *
 */
public interface Observer {
    void update(String message);
}
