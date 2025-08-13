package com.example.servingwebcontent.service;

import java.util.ArrayList;

import com.example.servingwebcontent.service.Interfaces.LibraryInterface;

public class LibraryManager implements LibraryInterface {
    private ArrayList<Object> list = new ArrayList<>();


    public ArrayList<Object> createObject(Object obj) {
        list.add(obj);
        return list;
    }


    public ArrayList<Object> deleteObject(Object obj) {
        list.remove(obj);
        return list;
    }


    public ArrayList<Object> editObject(Object obj) {
        // Ví dụ đơn giản: xóa rồi thêm lại
        deleteObject(obj);
        createObject(obj);
        return list;
    }

    
    public void readObject() {
        System.out.println("=== readObject() ===");
        for (Object o : list) {
            System.out.println(o.getClass().getSimpleName() + ": " + o);
        }
    }

  
    public void readObject(ArrayList<Object> o) {
        System.out.println("=== readObject(list) ===");
        for (Object x : o) {
            System.out.println(x.getClass().getSimpleName() + ": " + x);
        }
    }


    public void test() {
        readObject();
    }
}
