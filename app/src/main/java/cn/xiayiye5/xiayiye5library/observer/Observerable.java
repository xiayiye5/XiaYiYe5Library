package cn.xiayiye5.xiayiye5library.observer;

/*
 * @author : xiayiye5
 * @date : 2021/2/19 18:21
 * 类描述 :
 */

/***
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * @author jstao
 *
 */
public interface Observerable {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObserver();

}
