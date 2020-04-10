package com.association;

public class Test {
    public static void main(String[] args) {
        int size = 5; //定义数组大小

        //定义数组
        double [] mylist=new double[size];
        mylist[0]=0.1;
        mylist[1]=0.2;
        mylist[2]=0.3;
        mylist[3]=0.4;
        mylist[4]=0.5;

        double sum= 0;

        for (int i = 0; i < mylist.length; i++) {
            sum += mylist[i];
        }

        System.out.println("数组元素总和为："+sum);

    }

}
