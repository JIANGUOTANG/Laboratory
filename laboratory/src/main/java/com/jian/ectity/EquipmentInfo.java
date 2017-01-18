package com.jian.ectity;

import java.io.Serializable;

/**
 * Created by jian on 2016/9/3.
 */
public class EquipmentInfo implements Serializable{
    private String equipID;//设备ID
    private String equipName;//设置名称
    private String laboratory;//实验室

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    private String way;    //方法
    private String option;//功能

    private String firm;//厂商
    private String equipDescribe;//设备描述
    private String producingArea;//产地
    private String species;//类别

    public EquipmentInfo(String equipID, String equipName, String laboratory, String way, String option, String firm, String equipDescribe, String producingArea, String species, String buyDate) {
        this.equipID = equipID;
        this.equipName = equipName;
        this.laboratory = laboratory;
        this.way = way;
        this.option = option;
        this.firm = firm;
        this.equipDescribe = equipDescribe;
        this.producingArea = producingArea;
        this.species = species;
        this.buyDate = buyDate;
    }

    public String getBuyDate() {

        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getEquipID() {
        return equipID;
    }

    public void setEquipID(String equipID) {
        this.equipID = equipID;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getEquipDescribe() {
        return equipDescribe;
    }

    public void setEquipDescribe(String equipDescribe) {
        this.equipDescribe = equipDescribe;
    }

    public String getProducingArea() {
        return producingArea;
    }

    public void setProducingArea(String producingArea) {
        this.producingArea = producingArea;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    private String buyDate;//购置日期


}
