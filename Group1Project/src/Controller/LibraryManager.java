package Controller;

import Controller.Interfaces.LibraryInterface;
import java.util.ArrayList;

public class LibraryManager implements LibraryInterface {
    private ArrayList<Object> list = new ArrayList<>();

    @Override
    public ArrayList<Object> createObject(Object obj) {
        list.add(obj);
        return list;
    }

    @Override
    public ArrayList<Object> deleteObject(Object obj) {
        list.remove(obj);
        return list;
    }

    @Override
    public ArrayList<Object> editObject(Object obj) {
        // Ví dụ đơn giản: xóa rồi thêm lại
        deleteObject(obj);
        createObject(obj);
        return list;
    }

    @Override
    public void readObject() {
        System.out.println("=== readObject() ===");
        for (Object o : list) {
            System.out.println(o.getClass().getSimpleName() + ": " + o);
        }
    }

    @Override
    public void readObject(ArrayList<Object> o) {
        System.out.println("=== readObject(list) ===");
        for (Object x : o) {
            System.out.println(x.getClass().getSimpleName() + ": " + x);
        }
    }

    @Override
    public void test() {
        readObject();
    }
}
