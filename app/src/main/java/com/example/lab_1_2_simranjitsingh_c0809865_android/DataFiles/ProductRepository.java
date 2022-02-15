package com.example.lab_1_2_simranjitsingh_c0809865_android.DataFiles;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.lab_1_2_simranjitsingh_c0809865_android.Database.ProductRoomDb;
import com.example.lab_1_2_simranjitsingh_c0809865_android.Models.Product;

import java.util.List;

public class ProductRepository {

    private String TAG = this.getClass().getName();
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ProductRepository(Application application) {
        ProductRoomDb db = ProductRoomDb.getInstance(application);
        productDao = db.productDao();
        allProducts = productDao.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }


    public LiveData<List<Product>> getFirstProduct() {
        return productDao.getFirstProduct();
    }

    public void insert(Product product) {

        ProductRoomDb.databaseWriteExecutor.execute(() -> productDao.insert(product));
    }


    public void update(int id, String name, String desc, Double price, Double lat, Double lon) {

        ProductRoomDb.databaseWriteExecutor.execute(() -> productDao.update(id,name,desc,price,lat,lon));

    }
    public void delete_by_id(int id) {

        ProductRoomDb.databaseWriteExecutor.execute(() -> productDao.deletebyid(id));

    }

    public LiveData<List<Product>> getProductByID(int id) {
        return productDao.getProductById(id);

    }
}
