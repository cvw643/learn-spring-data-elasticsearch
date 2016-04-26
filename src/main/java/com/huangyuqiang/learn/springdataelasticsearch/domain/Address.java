package com.huangyuqiang.learn.springdataelasticsearch.domain;

/**
 * Created by huangyuqiang on 2016/4/26.
 */
public class Address {
    private int code;
    private String city;
    private String street;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
