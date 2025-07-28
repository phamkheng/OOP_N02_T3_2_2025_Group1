package com.example.servingwebcontent.Controller;

import com.example.servingwebcontent.Controller.Interfaces.LibraryInterface;

public class Callback {
    private LibraryInterface callbackReference;

    public Callback(LibraryInterface cbr) {
        this.callbackReference = cbr;
    }

    public void test() {
        callbackReference.test();
        System.out.println("call back: " + callbackReference.getClass().getName());
    }

    public void testObject(Object obj) {
        callbackReference.createObject(obj);
        callbackReference.test();
        System.out.println("call back: " + callbackReference.getClass().getName());
    }
}
