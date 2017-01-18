package com.jian.ectity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
/**
 * Created by jian on 2016/8/5.
 */
@Table(name="notifavationInfo")
public class NotifavationInfo implements Serializable{
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSecontitle() {
        return Secontitle;
    }

    public void setSecontitle(String secontitle) {
        Secontitle = secontitle;
    }
    @Column(name = "id", isId = true)
    private int id;
@Column(name = "title")
    private String Title;
    @Column(name = "time")
    private String Time;

    private String Secontitle;
    public NotifavationInfo(String title, String time, String detail, String url, String Secontitle) {
        Title = title;
        Time = time;
        this.detail = detail;
        this.url = url;
        this.Secontitle = Secontitle;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIamge() {
        return iamge;
    }

    public void setIamge(String iamge) {
        this.iamge = iamge;
    }
    @Column(name = "detail")
    private String detail;
    @Column(name = "url")
    private String url;

    private String iamge;
}
