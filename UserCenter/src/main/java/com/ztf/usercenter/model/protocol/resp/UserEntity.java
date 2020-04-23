package com.ztf.usercenter.model.protocol.resp;

public class UserEntity {
    private String calendar;
    private String headImg;
    private String introduction;
    private int phoneNum;
    private String sex;
    private String userAddress;
    private int userId;
    private String userPassWord;

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "calendar='" + calendar + '\'' +
                ", headImg='" + headImg + '\'' +
                ", introduction='" + introduction + '\'' +
                ", phoneNum=" + phoneNum +
                ", sex='" + sex + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userId=" + userId +
                ", userPassWord='" + userPassWord + '\'' +
                '}';
    }
}
