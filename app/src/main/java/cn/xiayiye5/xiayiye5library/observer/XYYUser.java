package cn.xiayiye5.xiayiye5library.observer;

/**
 * @author : xiayiye5
 * @date : 2021/2/23 15:20
 * 类描述 :
 */
public class XYYUser implements XYYObserver {
    private String name;
    private int age;

    public XYYUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void update(String msg) {
        System.out.println(name + "" + age + "岁收到了" + msg);
    }
}
