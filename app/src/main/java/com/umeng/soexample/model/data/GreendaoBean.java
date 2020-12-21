package com.umeng.soexample.model.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreendaoBean {

    @Id
    Long id;
    int price;
    int number;
    String desc;
    Boolean cb;
    String img;
    @Generated(hash = 2145053565)
    public GreendaoBean(Long id, int price, int number, String desc, Boolean cb,
            String img) {
        this.id = id;
        this.price = price;
        this.number = number;
        this.desc = desc;
        this.cb = cb;
        this.img = img;
    }
    @Generated(hash = 1389728398)
    public GreendaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Boolean getCb() {
        return this.cb;
    }
    public void setCb(Boolean cb) {
        this.cb = cb;
    }
    public String getImg() {
        return this.img;
    }
    public void setImg(String img) {
        this.img = img;
    }

}
