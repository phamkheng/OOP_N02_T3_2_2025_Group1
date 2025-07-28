package com.example.servingwebcontent.Controller.Interfaces;

import java.util.ArrayList;

public interface LibraryInterface {
   
    public ArrayList<Object> createObject(Object obj);

    public ArrayList<Object> deleteObject(Object obj);

    public ArrayList<Object> editObject(Object obj);

    public void readObject();
    public void readObject(ArrayList<Object> o);
    public void test();
}