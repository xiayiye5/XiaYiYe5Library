package cn.xiayiye5.xiayiye5library.bean;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @author : xiayiye5
 * @date : 2021/2/4 19:38
 * 类描述 :
 */
@Route(path = "/service/json")
public class ObjName implements SerializationService {
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

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return new Gson().fromJson(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return new Gson().toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return new Gson().fromJson(input, clazz);
    }

    @Override
    public void init(Context context) {

    }
}
