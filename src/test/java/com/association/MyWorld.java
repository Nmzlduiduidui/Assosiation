package com.association;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 将任何对象序列化到单一流中，就可以恢复出与我们写时一样的对象网，
 * 并且没有任何意外的重复复制出来的对象。
 */

class House implements Serializable {
}

class Animal implements Serializable {
    private String name;
    private House preferredHouse; //首选住宅

    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }



    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", preferredHouse=" + preferredHouse +
                '}' + "\n";
    }
}

public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        //通过字节数组来使用对象序列化
        //从而实现对任何Serializable对象的深刻复制，复制的是整个对象网，不仅仅是基本对象及其引用
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("dog", house));
        animals.add(new Animal("cat", house));
        animals.add(new Animal("pig", house));

        System.out.println("animal:" + animals);

        //将两个对象序列化成独立文件
        ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
        ObjectOutputStream out1 = new ObjectOutputStream(bos1);
        // 将对象序列化到单一的流
        //序列化两次
        out1.writeObject(animals);
        out1.writeObject(animals);

        //反序列
        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(bos1.toByteArray()));

        List animals1 = (List) in1.readObject();
        List animals2 = (List) in1.readObject();

        //写到不同的流
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        ObjectOutputStream out2 = new ObjectOutputStream(bos2);
        out2.writeObject(animals);

        //反序列化
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(bos2.toByteArray()));

        List animals3 = (List) in2.readObject();

        //将对象序列化到单一的流，反序列化恢复出的对象网是一样的
        System.out.println("animals1:" + animals1);
        System.out.println("animals2:" + animals2);
        System.out.println("animals3:" + animals3);

        //每次运行对象将会处于不同的内存地址
    }


}
