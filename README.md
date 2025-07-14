# Library Management System 🎉

<p align="center">
  <img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=24&pause=1000&color=FF4B4B&center=true&vCenter=true&width=500&height=50&lines=Chào+mừng+đến+với+ứng+dụng+thư+viện;Nhóm+01+OOP_N02_T3_2_2025_" alt="Typing SVG" />
</p>

<p align="center">
  <a href="https://github.com/phamkheng/OOP_N02_T3_2_2025_Group1">
    <img src="https://raw.githubusercontent.com/acervenky/animated-github-badges/master/assets/devbadge.gif" width="50" alt="Developer Badge" />
    <img src="https://raw.githubusercontent.com/acervenky/animated-github-badges/master/assets/contribbadge.gif" width="50" alt="Contributor Badge" />
  </a>
</p>

---

📋 Mục lục

Giới thiệu

Thành viên

Ứng dụng

Yêu cầu

Cài đặt--Chạy

Cấu trúc thư mục

Mô tả đối tượng

Ví dụ

Hướng phát triển

Bản quyền

✨ Giới thiệu

Đây là ứng dụng Quản lý Thư viện được xây dựng theo hướng lập trình hướng đối tượng (OOP), cho phép:

Quản lý sách (Book)

Quản lý bạn đọc (Reader)

Theo dõi lịch sử mượn/trả (Loan)

👥 Thành viên

Phạm Năng Khang (24100032) – phamkheng

Trần Quốc Việt Hùng (24100015) – hungspec

🌐 Ứng dụng

Thêm, sửa, xóa sách và bạn đọc

Cho mượn sách, trả sách, xem lịch sử

Kiểm tra trạng thái sách (còn hay hết)
## 📊 Activity Diagram – Mượn sách

![Activity Diagram Mượn sách](https://github.com/phamkheng/OOP_N02_T3_2_2025_Group1/raw/main/muon-sach.png)


📆 Yêu cầu

Java 11 hoặc cao hơn

Maven hoặc IDE hỗ trợ (IntelliJ, Eclipse…)

⚙️ Cài đặt & Chạy

git clone https://github.com/phamkheng/OOP_N02_T3_2_2025_Group1.git
cd OOP_N02_T3_2_2025_Group1

🗂️ Cấu trúc thư mục

OOP_N02_T3_2_2025_Group1/
├── src/
│   ├── Book.java
│   ├── Reader.java
│   ├── Loan.java
├── Main.java

📚 Mô tả đối tượng

Book: Thuộc tính và hành vi của sách

Reader: Quản lý thông tin bạn đọc

Loan: Lưu vết giao dịch mượn/trả

📖 Ví dụ

Book book = new Book("Harry Potter", "J.K. Rowling");
Reader reader = new Reader("Nguyen Van A");
Loan loan = new Loan(book, reader);
loan.borrow();

✨ Hướng phát triển

Thêm giao diện GUI

Kết nối cơ sở dữ liệu

Tích hợp API REST

📄 Bản quyền

MIT License. © 2025


1. **Clone repo:**
   ```bash
   git clone https://github.com/phamkheng/OOP_N02_T3_2_2025_Group1.git
   cd OOP_N02_T3_2_2025_Group1
