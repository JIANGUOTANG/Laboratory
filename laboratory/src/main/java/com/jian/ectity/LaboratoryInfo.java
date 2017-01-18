package com.jian.ectity;

import java.io.Serializable;

/**
 * Created by jian on 2016/9/3.
 */
public class LaboratoryInfo implements Serializable{
    private String laboratoryID;//ID
    private String laboratoryName;//名称
    private String laboratoryUnit;//单位
    private String principal;    //负责人
    private String equip_amount;//设备数
    private String contain;//容量
    private String location;//位置
    private String briefing;//简介
    private String condition;//状态

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLaboratoryID() {
        return laboratoryID;
    }
    public void setLaboratoryID(String laboratoryID) {
        this.laboratoryID = laboratoryID;
    }
    public String getLaboratoryName() {
        return laboratoryName;
    }
    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }
    public String getLaboratoryUnit() {
        return laboratoryUnit;
    }
    public void setLaboratoryUnit(String laboratoryUnit) {
        this.laboratoryUnit = laboratoryUnit;
    }
    public String getPrincipal() {
        return principal;
    }
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
    public String getEquip_amount() {
        return equip_amount;
    }
    public void setEquip_amount(String equip_amount) {
        this.equip_amount = equip_amount;
    }
    public String getContain() {
        return contain;
    }
    public void setContain(String contain) {
        this.contain = contain;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getBriefing() {
        return briefing;
    }
    public void setBriefing(String briefing) {
        this.briefing = briefing;
    }
    public LaboratoryInfo(String laboratoryID, String laboratoryName, String laboratoryUnit, String principal, String equip_amount, String contain, String location, String briefing,String condition) {
        this.laboratoryID = laboratoryID;
        this.laboratoryName = laboratoryName;
        this.laboratoryUnit = laboratoryUnit;
        this.principal = principal;
        this.equip_amount = equip_amount;
        this.contain = contain;
        this.location = location;
        this.briefing = briefing;
        this.condition = condition;
    }
}
