# Textbook Library Management System 🎉

<p align="center">
  <img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=24&pause=1000&color=FF4B4B&center=true&vCenter=true&width=500&height=50&lines=Chào+mừng+đến+với+ứng+dụng+quản+lí+thư+viện;Nhóm+01+OOP_N02_T3_2_2025_" alt="Typing SVG" />
</p>

<p align="center">
  <a href="https://github.com/phamkheng/OOP_N02_T3_2_2025_Group1">
    <img src="https://raw.githubusercontent.com/acervenky/animated-github-badges/master/assets/devbadge.gif" width="70" alt="Developer Badge" />
    <img src="https://raw.githubusercontent.com/acervenky/animated-github-badges/master/assets/contribbadge.gif" width="70" alt="Contributor Badge" />
  </a>
</p>

---

📋 Mục lục

1. [Giới thiệu]
2. [Thành viên] 
3. [Ứng dụng] 
4. [Yêu cầu]  
5. [Cài đặt & Chạy] 
6. [Activity Diagram – Mượn sách] 
7. [Activity Diagram – Trả sách]  
8. [Cấu trúc thư mục]
9. [Mô tả đối tượng]  
10. [Ví dụ sử dụng] 
11. [Hướng phát triển] 
12. [Bản quyền]
Cài đặt--Chạy


## ✨ Giới thiệu

Ứng dụng **Quản lý Thư viện giáo trình** được xây dựng theo phong cách **Lập trình Hướng đối tượng (OOP)**, giúp bạn:

- Quản lý giáo trình (`Book`)  
- Quản lý bạn đọc (`Reader`)  
- Theo dõi lịch sử mượn/trả (`Loan`)

---

## 👥 Thành viên

| Họ tên               | MSSV      | GitHub                        |
|----------------------|-----------|-------------------------------|
| Phạm Năng Khang      | 24100032  | [@phamkheng](https://github.com/phamkheng) |
| Trần Quốc Việt Hùng  | 24100015  | [@hungspec](https://github.com/hungspec)   |
| Phạm Việt Khoa       | 24100058  | [@pvkhoa](https://github.com/pvkhoa)       |
| Nguyễn Lệ Thu        |           | [@nglthu](https://github.com/nglthu)       |

---

## 🧠 Phân tích đối tượng

### 1. 👤 Người đọc (`Reader`)
- **Thuộc tính**: ID, tên, số điện thoại, email.
- **Chức năng**:
  - Đăng ký người đọc
  - Hiển thị thông tin
  - Xóa người đọc khỏi danh sách

### 2. 🧾 Giáo trình (`Book`)
- **Thuộc tính**: ID, tên, tác giả, trang thái (có sẵn/ trống), số lượng.
- **Chức năng**:
  - cập nhập giáo trình mới
  - Hiển thị danh sách giáo trình
  - Tìm kiếm giáo trình
  - Xóa giáo trình khỏi danh sách

### 3. 📦 Dịch vụ mượn/trả (`Loan`)
- **Thuộc tính**: ID, book, reader, ngày mượn, ngày trả, trạng thái
- **Chức năng**:
  - Hiển thị thông tin
  - Cập nhật sau khi mượn/trả

---
## 🗂️ Cấu trúc thư mục
// chưa làm

---
## 🛠️ Chức năng chính

- **Quản lý người đọc**:
  - Thêm / Sửa / Xóa người đọc

- **Quản lý giáo trình**:
  - Thêm / Sửa / Xóa giáo trình
  - Cập nhật số lượng giáo trình sau mỗi lần mượn/trả

- **Quản lý mượn/trả**:
  - cập nhập ngày mượn/trả
  - Hiển thị danh sách mượn

- **Lưu trữ dữ liệu**:
  - Dữ liệu được lưu vào file nhị phân 
  - Sử dụng `ObjectOutputStream`, `ObjectInputStream`
  - Áp dụng `ArrayList`, `Map`,... để quản lý dữ liệu trong bộ nhớ

---

## 📆 Yêu cầu

- Java 11 hoặc cao hơn  
- Maven hoặc IDE Java (IntelliJ IDEA, Eclipse…)

---

## 📊 Class Diagram 
![Class Diagram ](../main/images/class-diagram.png)

## 📊 Activity Diagram – Mượn giáo trình
![Activity Diagram Mượn sách](../main/images/muonsach.png)
## 📊 Activity Diagram – Trả giáo trình 
![Activity Diagram ](../main/images/trasach.png)
## 📊 Activity Diagram – tìm giáo trình  
![Activity Diagram ](../main/images/timsach.png)
## 📊 Activity Diagram – đăng ký bạn đọc mới
![Activity Diagram ](../main/images/dangkybandocmoi.png)


📚 Mô tả đối tượng
Book

Thuộc tính: title, author, numPages, status (Available / Unavailable)

Hành vi: checkAvailability(), markAsBorrowed(), markAsReturned(), display()

Reader

Thuộc tính: readerID, name, email, phone, borrowedBooks (List<Loan>)

Hành vi: borrowBook(Book), returnBook(Book), viewBorrowHistory()

Loan

Thuộc tính: loanID, book, reader, loanDate, returnDate, status

Hành vi: markReturned(Date), isOverdue(Date)

---
🚀 Hướng phát triển
Thêm giao diện đồ họa (GUI)

Kết nối cơ sở dữ liệu (JDBC / MySQL / SQLite)

Tích hợp RESTful API (Spring Boot)

## 🖼️ Giao diện chương trình (Console)
// chưa đẩy 

---

## 🌟 Chức năng nổi bật

- **Xử lý nhập sai dữ liệu, đăng kí**
- **Tìm kiếm người đọc, giáo trình qua mã số, tên**
- **Hiển thị dữ liệu chi tiết và rõ ràng dưới giao diện console**

---

## ⚙️ Cài đặt & Chạy

```bash
git clone https://github.com/phamkheng/OOP_N02_T3_2_2025_Group1.git
cd OOP_N02_T3_2_2025_Group1
# Mở project bằng IDE hoặc:
# javac src/*.java
# java Main
---
## 📚 Tài liệu tham khảo

- Slide bài giảng môn Lập trình Hướng Đối Tượng – GVHD: TS.Nguyễn Lệ Thu
- Java Docs – Oracle
- Stack Overflow – Community

---










