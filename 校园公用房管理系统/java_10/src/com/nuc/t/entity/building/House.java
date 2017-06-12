package com.nuc.t.entity.building;

/**
 * Created by mxwbq on 2017/5/31.
 */
public class House {
    private String id = null;  // 房屋编号(五个字符)
    private String belong = null;  // 所属单位(十个字符)
    private String position = null;  // 房屋位置(二十个字符)
    private double area = 0;  // 建筑面积(十个字符）
    private String usage = "办公"; // 使用方式：办公、教学、后勤（两个字符）
    private String serviceCondition = "否";  // 使用情况（一个字符）
    private String safetyCondition = "危险";  // 安全情况（两个字符）

    public House() {
    }

    public House(String id, String belong, String position, double area) {
        this.id = id;
        this.belong = belong;
        this.position = position;
        this.area = area;
    }

    public House(String id, String belong, String position, double area, String usage, String serviceCondition, String safetyCondition) {
        this.id = id;
        this.belong = belong;
        this.position = position;
        this.area = area;
        this.usage = usage;
        this.serviceCondition = serviceCondition;
        this.safetyCondition = safetyCondition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getServiceCondition() {
        return serviceCondition;
    }

    public void setServiceCondition(String serviceCondition) {
        this.serviceCondition = serviceCondition;
    }

    public String getSafetyCondition() {
        return safetyCondition;
    }

    public void setSafetyCondition(String safetyCondition) {
        this.safetyCondition = safetyCondition;
    }
}
