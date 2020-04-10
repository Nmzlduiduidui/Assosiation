package com.association;
/*
 * 需求：将构成系统状态的所有对象置入到一个容器当中，并在一个操作中将该容器直接写出。
 *       只需一次方法调用就能将其恢复。
 * */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Shape implements Serializable {
    public static  final int RED = 1, BLUE = 2, GREEN = 3;  //颜色
    private  int xPos, yPos, dimension;
    private static Random random = new Random(47);
    private static int counter = 0;

    public abstract void setColor(int newColor);

    public abstract int getColor();

    //初始化
    public Shape(int xVal, int yVal, int dim) {
        xPos = xVal;
        yPos = yVal;
        dimension = dim;
    }

    @Override
    public String toString() {
        return getClass()+"color["+getColor()+"]{" +
                "xPos=" + xPos +
                ", yPos=" + yPos +
                ", dimension=" + dimension +
                '}'+"\n";
    }

    public static Shape randomFactory() {

        int xVal = random.nextInt(100);
        int yVal = random.nextInt(100);
        int dim = random.nextInt(100);
        switch (counter++ % 3) {
            default:
            case 0:
                return new Cricle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);

        }
    }
}

//圆形
class Cricle extends Shape {
    private static  int color = RED;

    public static void serializeStaticState(ObjectOutputStream out)throws IOException {
        out.writeInt(color);

    }

    public static void deserializeStaticState(ObjectInputStream in)throws IOException {
        color = in.readInt();
    }

    public Cricle(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

//正方形
class Square extends Shape {
    private static int color;

    public static void serializeStaticState(ObjectOutputStream out)throws IOException {
        out.writeInt(color);

    }

    public static void deserializeStaticState(ObjectInputStream in)throws IOException {
        color = in.readInt();
    }

    public Square(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }

}

//线条型
class Line extends Shape {

    private  static int color;

    public static void serializeStaticState(ObjectOutputStream out)throws IOException {
        out.writeInt(color);

    }

    public static void deserializeStaticState(ObjectInputStream in)throws IOException {
        color = in.readInt();
    }

    public Line(int xVal, int yVal, int dim) {
        super(xVal, yVal, dim);
    }

    @Override
    public void setColor(int newColor) {
        color = newColor;
    }

    @Override
    public int getColor() {
        return color;
    }
}

public class StoreCADState {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //ArrayList用于保存class对象
        //  List<Class<? extends Shape>> shapeTypes = new ArrayList<Class<? extends Shape>>();

        //添加对类对象的引用
        // shapeTypes.add(Cricle.class);
        // shapeTypes.add(Square.class);
        //shapeTypes.add(Line.class);

        List<Shape> shapes = new ArrayList<Shape>();

        //做一些形状
        for (int i = 0; i < 10; i++) {
            shapes.add(Shape.randomFactory()); //每次调用randomFactory方法，都会使用不同的随机数作为shape的数据
        }

        //使所有static colors 为BLUE
        for (int i = 0; i < 10; i++) {
            ((Shape) shapes.get(i)).setColor(Shape.BLUE);
        }

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CAD.out"));
        // out.writeObject(shapeTypes);
        Line.serializeStaticState(out);
        Square.serializeStaticState(out);
        Cricle.serializeStaticState(out);

        out.writeObject(shapes);
        System.out.println(shapes);

    }

}