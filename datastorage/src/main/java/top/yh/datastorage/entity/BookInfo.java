package top.yh.datastorage.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @user
 * @date
 */
@Entity
public class BookInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String author;

    public BookInfo(String name, String author, String press, double price) {
        this.name = name;
        this.author = author;
        this.press = press;
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                ", price=" + price +
                '}';
    }

    private String press;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
