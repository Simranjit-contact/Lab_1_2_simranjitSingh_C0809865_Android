package com.example.lab_1_2_simranjitsingh_c0809865_android.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab_1_2_simranjitsingh_c0809865_android.model.Product;

import java.util.List;


@Dao
public abstract class ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(Product product);

    @Query("SELECT * FROM product_table ORDER BY product_name ASC")
    public abstract LiveData<List<Product>> getAllProducts();

    @Query("DELETE FROM product_table")
    public abstract void deleteAll();

    @Query("DELETE FROM product_table WHERE product_id=:id")
    public abstract void deletebyid(int id);

    @Query("SELECT * FROM product_table WHERE product_id=:id")
    public abstract LiveData<List<Product>> getProductById(int id);

    @Query("SELECT * FROM product_table ORDER BY product_id ASC limit 1")
    public abstract LiveData<List<Product>> getFirstProduct();

    @Query("UPDATE product_table SET product_name=:name,product_desc=:desc,product_price=:price,provider_lat=:lat,provider_long=:lon WHERE product_id =:id")
    public abstract void update(int id, String name, String desc, Double price, Double lat, Double lon);
}
