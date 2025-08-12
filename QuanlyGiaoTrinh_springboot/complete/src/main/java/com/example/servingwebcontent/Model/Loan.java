package com.example.servingwebcontent.Model;

public class Loan {
    public String loanID;
    public Book book;
    public Reader reader;
    public String borrowDate;
    public String returnDate;
    public String dueDate; 
    public String status = "Borrowed";

    public Loan() {
    }

    public Loan(String loanID, Book book, Reader reader, String borrowDate, String dueDate) {
        this.loanID = loanID;
        this.book = book;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null;
    }

    public String getLoanID() {
        return loanID;
    }

    public void setLoanID(String loanID) {
        this.loanID = loanID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void markReturned(String date) {
        this.returnDate = date;
        this.status = "Returned";
        if (book != null) {
            book.markAsReturned();
        }
    }

    public boolean isOverdue(String currentDate) {
        if (returnDate == null && dueDate != null) {
            return currentDate.compareTo(dueDate) > 0;
        }
        return false;
    }
}
