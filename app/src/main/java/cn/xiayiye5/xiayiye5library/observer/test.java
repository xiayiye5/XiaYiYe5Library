package cn.xiayiye5.xiayiye5library.observer;

/**
 * @author : xiayiye5
 * @date : 2021/1/27 19:33
 * 类描述 :
 */
public class test {
    public static void main(String[] args) {
        goPage();
    }

    private static void goPage() {
        System.out.println("测试代码版本回退");

        WechatServer server = new WechatServer();
        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");
        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInfomation("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        server.setInfomation("JAVA是世界上最好用的语言！");



        XYYObserver xyyObserver1 = new XYYUser("张三",17);
        XYYObserver xyyObserver2 = new XYYUser("李四",18);
        XYYObserver xyyObserver3 = new XYYUser("王五",19);
        XYYServer xyyServer = new XYYServer();
        xyyServer.register(xyyObserver1);
        xyyServer.register(xyyObserver2);
        xyyServer.register(xyyObserver3);
        xyyServer.sendMessage("发送成人礼-----------");
        xyyServer.notifyData();
        xyyServer.remove(xyyObserver1);
        xyyServer.sendMessage("二次成年礼------------");
        xyyServer.notifyData();
    }
}
