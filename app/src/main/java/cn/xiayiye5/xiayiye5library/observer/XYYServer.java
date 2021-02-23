package cn.xiayiye5.xiayiye5library.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xiayiye5
 * @date : 2021/2/23 15:22
 * 类描述 :
 */
public class XYYServer implements XYYObserverable {
    private final List<XYYObserver> list = new ArrayList<>();
    private String msg;

    @Override
    public void register(XYYObserver o) {
        //添加数据
        list.add(o);
    }

    @Override
    public void remove(XYYObserver o) {
        if (list.isEmpty()) {
            return;
        }
        list.remove(o);
    }

    @Override
    public void notifyData() {
        for (XYYObserver xyyObserver : list) {
            xyyObserver.update(msg);
        }
    }

    public void sendMessage(String msg) {
        this.msg = msg;
    }
}
