package com.scrachx.foodfacts.checker.data.db.model;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity(indexes = {
        @Index(value = "barcode", unique = true)
})
public class History {

    @Id private Long id;
    private String title;
    private String brands;
    private String url;
    private Date lastSeen;
    private String barcode;
    private String quantity;
    private String grade;

    @Generated(hash = 869423138)
    public History() {
    }

    @Generated(hash = 1654388139)
    public History(Long id, String title, String brands, String url, Date lastSeen,
            String barcode, String quantity, String grade) {
        this.id = id;
        this.title = title;
        this.brands = brands;
        this.url = url;
        this.lastSeen = lastSeen;
        this.barcode = barcode;
        this.quantity = quantity;
        this.grade = grade;
    }

    public History(String title, String brands, String url, Date lastSeen,
                   String barcode, String quantity, String grade) {
        this.title = title;
        this.brands = brands;
        this.url = url;
        this.lastSeen = lastSeen;
        this.barcode = barcode;
        this.quantity = quantity;
        this.grade = grade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
