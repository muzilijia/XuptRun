package com.muzi.xuptrun.bean;

/**
 * 用于记录每天运动的总数据（以天为单位）
 * id
 * date      日期
 * duration  时长
 * calorie   卡路里
 * vector    速度
 * distance  里程
 * type      运动类型
 */

public class RunData {

    private int id ;
    private String date;
    private float duration;
    private float calorie;
    private float vector;
    private float distance;
    private String type;

    public String getDate_mouth() {
        return date_mouth;
    }

    public void setDate_mouth(String date_mouth) {
        this.date_mouth = date_mouth;
    }

    private String date_mouth;//201805这种形式

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public float getCalorie() {
        return calorie;
    }

    public void setCalorie(float calorie) {
        this.calorie = calorie;
    }

    public float getVector() {
        return vector;
    }

    public void setVector(float vector) {
        this.vector = vector;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
