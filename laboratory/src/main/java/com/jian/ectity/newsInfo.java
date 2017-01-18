package com.jian.ectity;

import java.io.Serializable;

/**
 * Created by jian on 2016/8/30.
 */
public class newsInfo implements Serializable{
    private String Title;
    private String SecondTitle;
    private String Data;
    private String Detail;
    private String  imagerl;
    private int Type;

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public newsInfo(String title, String secondTitle, String data, String detail, String imagerl, int type) {
        Title = title;
        SecondTitle = secondTitle;
        Data = data;
        Detail = detail;
        this.imagerl = imagerl;
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSecondTitle() {
        return SecondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        SecondTitle = secondTitle;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getImageUrl() {
        return imagerl;
    }

    public void setImageUrl(String imagerl) {
        this.imagerl = imagerl;
    }
}
