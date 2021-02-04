package cn.xiayiye5.xiayiye5library.bean;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 19:38
 * 类描述 :
 */
public class ObjName {
    public ObjName() {
    }

    public ObjName(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String name;
    public int age;
    public String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
