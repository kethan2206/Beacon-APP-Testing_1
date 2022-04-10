package com.example.testing2;

public class Coupons {

    private String brand;
    private String coins;
    private String couponCode;
    public Coupons() {
    }

    public Coupons(String brand, String coins) {
        this.brand = brand;
        this.coins = coins;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coupons) {
        this.coins = coupons;
    }

    public Coupons(String brand, String coins, String couponCode) {
        this.brand = brand;
        this.coins = coins;
        this.couponCode = couponCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
