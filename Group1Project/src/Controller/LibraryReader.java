package Controller;

import java.util.ArrayList;
import Model.Reader;

public class LibraryReader {

    private ArrayList<Reader> listReaders = new ArrayList<>();

    // Thêm độc giả, kiểm tra trùng ID
    public boolean addReader(Reader r) {
        if (r == null || r.readerID == null || r.readerID.isEmpty()) {
            System.out.println("Thông tin độc giả không hợp lệ.");
            return false;
        }

        for (Reader existing : listReaders) {
            if (existing.readerID.equals(r.readerID)) {
                System.out.println("Độc giả với ID này đã tồn tại.");
                return false;
            }
        }

        listReaders.add(r);
        return true;
    }

    // Xóa độc giả theo ID
    public boolean deleteReader(String readerId) {
        for (int i = 0; i < listReaders.size(); i++) {
            if (listReaders.get(i).readerID.equals(readerId)) {
                listReaders.remove(i);
                return true;
            }
        }

        System.out.println("Không tìm thấy độc giả với ID: " + readerId);
        return false;
    }

    // Sửa thông tin độc giả
    public boolean editReader(String readerId, String newName, String newEmail, String newPhone) {
        for (Reader r : listReaders) {
            if (r.readerID.equals(readerId)) {
                if (newName != null && !newName.isEmpty()) r.name = newName;
                if (newEmail != null && !newEmail.isEmpty()) r.email = newEmail;
                if (newPhone != null && !newPhone.isEmpty()) r.phone = newPhone;
                return true;
            }
        }

        System.out.println("Không tìm thấy độc giả để chỉnh sửa.");
        return false;
    }

    // In danh sách độc giả
    public void readReaders() {
        if (listReaders.isEmpty()) {
            System.out.println("Không có độc giả nào trong hệ thống.");
            return;
        }

        for (Reader r : listReaders) {
            System.out.println("Reader ID: " + r.readerID);
            System.out.println("Name: " + r.name);
            System.out.println("Email: " + r.email);
            System.out.println("Phone: " + r.phone);
            System.out.println("-----------------------");
        }
    }

    // Trả về danh sách độc giả (nếu cần xử lý từ lớp khác)
    public ArrayList<Reader> getAllReaders() {
        return listReaders;
    }

    // Tìm độc giả theo ID
    public Reader findReaderById(String readerId) {
        for (Reader r : listReaders) {
            if (r.readerID.equals(readerId)) {
                return r;
            }
        }
        return null;
    }
}
