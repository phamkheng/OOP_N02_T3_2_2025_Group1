package Model;

import java.util.Objects;

public class LibraryItem implements Comparable<LibraryItem> {
    private String title;
    private int quantity;

    public LibraryItem(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public LibraryItem() {}

    @Override
    public int compareTo(LibraryItem other) {
        // Sắp xếp giảm dần theo số lượng
        return Integer.compare(other.quantity, this.quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryItem other = (LibraryItem) o;
        return quantity == other.quantity && title.equals(other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, quantity);
    }

    // Getter & Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

