package cn.xiayiye5.xiayiye5library.other;

import java.util.Random;

/**
 * @author : xiayiye5
 * @date : 2021/1/27 19:34
 * 类描述 :
 */
public class demo {
    public static void main(String[] args) {
        goHome();
        goPage();
        select(0, "1", "2", "3", "4");
    }

    private static void goPage() {
        select(6, "1", "6");
    }

    private static void select(int num, String... data) {
        if (num != 0) {
            for (String datum : data) {
                System.out.println(datum);
            }
        }
    }

    private static void goHome() {
        Random random = new Random(100);
        int i = random.nextInt();
        if (i > 0 && i < 33) {
            System.out.println(i);
        } else if (i >= 33 && i < 66) {
            System.out.println(i);
        } else {
            System.out.println(i);
        }
    }
}
