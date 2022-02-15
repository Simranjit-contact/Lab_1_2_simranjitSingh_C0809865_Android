package com.example.lab_1_2_simranjitsingh_c0809865_android.Models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lab_1_2_simranjitsingh_c0809865_android.DataFiles.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;
    private final LiveData<List<Product>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        repository = new ProductRepository(application);
        allProducts = repository.getAllProducts();
    }

    public LiveData<List<Product>> getFirstProduct() {return repository.getFirstProduct();}
    public LiveData<List<Product>> getAllProduct() {return allProducts;}
    public LiveData<List<Product>> getProductById(int id) {return repository.getProductByID(id);}
    public void update(int id, String name, String desc, Double price, Double lat, Double lon) {repository.update(id,name,desc,price,lat,lon);}
    public void delete_by_id(int id) {repository.delete_by_id(id);}

    public void insert(Product product) {repository.insert(product);}
}
