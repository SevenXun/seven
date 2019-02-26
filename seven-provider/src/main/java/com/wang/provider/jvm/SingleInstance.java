package com.wang.provider.jvm;

public class SingleInstance {
    private  static class Holder{
        public static MyByteClassLoader myByteClassLoader;
        static {
            System.out.println("Holder............");
        }
    }

    public static MyByteClassLoader getInstance(){
        return null;
    }

    public static void main(String[]args){
        System.out.println("xxxxxxxxx");
        SingleInstance.getInstance();
    }
}
