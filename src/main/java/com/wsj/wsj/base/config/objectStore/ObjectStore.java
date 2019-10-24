package com.wsj.wsj.base.config.objectStore;

import com.wsj.wsj.base.config.annotation.AnnotationUtil;
import com.wsj.wsj.base.util.ClasspathPackageScanner;
import com.wsj.wsj.base.util.ProjectInfoUtil;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class ObjectStore {
    private HashMap<Class, Object> objectStore = new HashMap<>();

    public HashMap getObjectStore(){
        return objectStore;
    }

    public void run() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
        List<Class> lst = ClasspathPackageScanner.getAllClass(ProjectInfoUtil.getJavaSourcePath());
        for (Class cla : lst) {
            add(cla);
        }
    }

    private void add(Class cla) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class inter = com.wsj.wsj.base.config.objectStore.inter.ObjectStore.class;
        if (cla != inter && inter.isAssignableFrom(cla)) {
            addByInterface(cla);
            return;
        }

        Class annot = com.wsj.wsj.base.config.objectStore.annotation.ObjectStore.class;
        if (AnnotationUtil.isAssigned(annot, cla)) {
            addByAnnotation(cla);
            return;
        }
    }

    private void addByInterface(Class cla) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method[] interMethods = com.wsj.wsj.base.config.objectStore.inter.ObjectStore.class.getMethods();
        if (interMethods.length > 0) {
            String methodName = interMethods[0].getName();
            Method getInstance = cla.getDeclaredMethod(methodName);
            Object obj = getInstance.invoke(null, null);
            getObjectStore().put(cla, obj);
        }
    }

    private void addByAnnotation(Class cla) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Constructor constructor = cla.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(null);
        constructor.setAccessible(false);
        getObjectStore().put(cla, o);
    }

    private volatile static ObjectStore INSTANCE;

    private ObjectStore(){
        // 避免反射破坏
        if (INSTANCE != null) {
            throw new RuntimeException("对象已存在，不允许创建多个实例");
        }
    }

    public static ObjectStore getInstance(){
        if (INSTANCE == null) {
            synchronized (ObjectStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ObjectStore();
                }
            }
        }
        return INSTANCE;
    }

    // 避免克隆破坏,暂时没有实现序列化接口
    private Object readResolve(){
        return INSTANCE;
    }
}
