package com.jian.ectity;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by jian on 2016/9/3.
 */
@Table(name = "user")
public class UserInfo implements Serializable {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "userId")
    private String userId;//用户ID
    @Column(name = "name")
    private String name;//姓名
    @Column(name = "commission")
    private String commission;//权限
    @Column(name = "Unit")
    private String Unit;    //单位
    @Column(name = "sex")
    private String sex;//性别
    @Column(name = "email")
    private String email;//邮箱
    @Column(name = "birthday")
    private String birthday;//生日
    @Column(name = "phone")
    private String phone;//电话
    @Column(name = "CardId")
    private String CardId;//身份证
    @Column(name = "office")
    private String office;//职称
    @Column(name = "inspect")
    private String inspect;//审核
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getInspect() {
        return inspect;
    }

    public void setInspect(String inspect) {
        this.inspect = inspect;
    }

    public UserInfo(String userId, String name, String commission, String unit, String sex, String email, String birthday, String phone, String cardId, String office, String inspect) {

        this.userId = userId;
        this.name = name;
        this.commission = commission;
        Unit = unit;
        this.sex = sex;
        this.email = email;
        this.birthday = birthday;
        this.phone = phone;
        CardId = cardId;
        this.office = office;
        this.inspect = inspect;
    }
}
