package Controller;

import java.util.ArrayList;

import java.util.Scanner;

import Model.Book;

public class LibraryBook {

    ArrayList<Book> listBooks = new ArrayList<>();
    public ArrayList<Book> getListBooks() {
        return listBooks;
    }
    public ArrayList<Book> addBook(Book b) {
    try {
        listBooks.add(b);
        System.out.println("Thêm sách thành công.");
    } catch (Exception e) {
        System.out.println("Lỗi khi thêm sách: " + e.getMessage());
    }
    return listBooks;
}
    public ArrayList<Book> deleteBook(String bookId) {
        try {
            for (int i = 0; i < listBooks.size(); i++) {
                if (listBooks.get(i).getBookID().equals(bookId)) {
                    listBooks.remove(i);
                    System.out.println("Sách đã được xóa thành công.");
                    return listBooks;
                }
            }
            System.out.println("Không tìm thấy sách với ID: " + bookId);
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa sách: " + e.getMessage());
        }
        return listBooks;
    }

    public void readBooks() {
        try {
            for (Book book : listBooks) {
                book.display();
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi đọc danh sách sách: " + e.getMessage());
        }
    }

    public ArrayList<Book> editBook(String bookId) {
        Scanner sc = new Scanner(System.in);
        try {
            for (Book book : listBooks) {
                if (book.getBookID().equals(bookId)) {
                    System.out.print("Enter new title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter new author: ");
                    String newAuthor = sc.nextLine();

                    book.setTitle(newTitle);
                    book.setAuthor(newAuthor);
                    System.out.println("Sửa sách thành công.");
                    break;
                }
            }
         } catch (Exception e) {
            System.out.println("Lỗi khi chỉnh sửa sách: " + e.getMessage());
        }finally {

        sc.close(); 

        sc.close();

    }
        return listBooks;
    }

    public void findBookByTitle(String keyword) {
        try {
            for (Book book : listBooks) {
                if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                    book.display();
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi tìm sách: " + e.getMessage());
        }
    }

    public void showAvailableBooks() {
        try {
            for (Book book : listBooks) {
                if (book.isAvailable()) {
                    book.display();
                    System.out.println();
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi hiển thị sách có sẵn: " + e.getMessage());
        }
    }

    public void borrowBook(String bookId) {
        try {
            for (Book book : listBooks) {
                if (book.getBookID().equals(bookId)) {
                    if (book.isAvailable()) {
                        book.markAsBorrowed();
                        System.out.println("Đã mượn sách thành công.");
                    } else {
                        System.out.println("Sách hiện không có sẵn.");
                    }
                    return;
                }
            }
            System.out.println("Không tìm thấy sách với ID đã nhập.");
        } catch (Exception e) {
            System.out.println("Lỗi khi mượn sách: " + e.getMessage());
        }
    }

    public void returnBook(String bookId) {
        try {
            for (Book book : listBooks) {
                if (book.getBookID().equals(bookId)) {
                    book.markAsReturned();
                    System.out.println("Đã trả sách thành công.");
                    return;
                }
            }
            System.out.println("Không tìm thấy sách với ID đã nhập.");
        } catch (Exception e) {
            System.out.println("Lỗi khi trả sách: " + e.getMessage());
        }
    }
}
