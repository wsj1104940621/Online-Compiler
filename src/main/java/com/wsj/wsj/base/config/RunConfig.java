package com.wsj.wsj.base.config;

import com.wsj.wsj.base.config.objectStore.ObjectStore;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RunConfig {
    private volatile static RunConfig instance;

    private RunConfig(){
        // 避免反射破坏
        if (instance != null) {
            throw new RuntimeException("对象已存在，不允许创建多个实例");
        }
    }

    public void run() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException, ClassNotFoundException {
        // 初始化对象库
        ObjectStore.getInstance().run();
    }

    public static RunConfig getInstance(){
        if (instance == null) {
            synchronized (RunConfig.class) {
                if (instance == null) {
                    instance = new RunConfig();
                }
            }
        }
        return instance;
    }

    // 避免克隆破坏,暂时没有实现序列化接口
    private Object readResolve(){
        return instance;
    }
}
