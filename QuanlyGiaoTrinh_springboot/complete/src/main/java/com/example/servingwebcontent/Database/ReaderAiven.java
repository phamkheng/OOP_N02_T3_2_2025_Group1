package com.example.servingwebcontent.Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.example.servingwebcontent.Model.Reader;

public class ReaderAiven {
    ArrayList<Reader> items = new ArrayList<Reader>();

    public ArrayList<Reader> readerAivenList() {
        try {
            myDBConnection my = new myDBConnection();
            Statement sta = my.getMyConn();
            ResultSet setdata = sta.executeQuery("SELECT * FROM Readers");
            System.out.println("Danh sách độc giả:");
            while (setdata.next()) {
                Reader reader = new Reader();
                reader.readerID = setdata.getString("readerID");
                reader.name = setdata.getString("name");
                reader.email = setdata.getString("email");
                reader.phone = setdata.getString("phone");
                System.out.println(reader.readerID + " | " + reader.name + " | " + reader.email);
                items.add(reader);
            }
            setdata.close();
            sta.close();
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách độc giả: " + e);
            e.printStackTrace();
        }
        return items;
    }

    public Reader getReaderById(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReaderById'");
    }
}