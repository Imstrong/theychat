package com.bruce.test;

public class ImplementTest implements B{
    public static void main(String[] args){

    }

    public void funcA() {

    }

    public void funcC() {

    }

    public void funcD() {

    }
}
interface A{
    void funcA();
}
interface C{
    void funcC();
}
interface D{
    void funcD();
}
interface B extends A,C,D{}