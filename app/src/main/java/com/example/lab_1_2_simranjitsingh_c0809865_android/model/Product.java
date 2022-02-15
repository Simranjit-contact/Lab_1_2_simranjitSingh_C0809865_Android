package com.example.lab_1_2_simranjitsingh_c0809865_android.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "product_table"

)
public class Product {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    private long id;

    @NonNull
    @ColumnInfo(name = "product_name")
    private String productName;

    @ColumnInfo(name = "product_desc")
    private String productDesc;

    @ColumnInfo(name = "product_price")
    private Double productPrice;


    @ColumnInfo(name = "provider_lat")
    private double providerLat;

    @ColumnInfo(name = "provider_long")
    private double providerLong;

    @Ignore
    public Product() {
    }
    public Product(long id, @NonNull String productName, String productDesc, Double productPrice,double providerLat, double providerLong) {
        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.providerLat = providerLat;
        this.providerLong = providerLong;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProviderLat() {
        return providerLat;
    }

    public void setProviderLat(double providerLat) {
        this.providerLat = providerLat;
    }

    public double getProviderLong() {
        return providerLong;
    }

    public void setProviderLong(double providerLong) {
        this.providerLong = providerLong;
    }


}
