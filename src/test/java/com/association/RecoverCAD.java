package com.association;

import java.io.*;
import java.util.List;

public class RecoverCAD {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("CAD.out"));
        Line.deserializeStaticState(in);
        Square.deserializeStaticState(in);
        Cricle.deserializeStaticState(in);
        List<Shape>shapes1=(List<Shape>)in.readObject();
        System.out.println(shapes1);

    }

}
