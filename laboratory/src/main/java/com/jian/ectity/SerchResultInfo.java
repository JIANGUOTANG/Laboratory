package com.jian.ectity;

/**
 * Created by jian on 2016/9/10.
 */

public class SerchResultInfo {
    private String Title;
    private String ID;
    private String url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SerchResultInfo(String title, String ID, String url) {

        Title = title;
        this.ID = ID;
        this.url = url;
    }
}
