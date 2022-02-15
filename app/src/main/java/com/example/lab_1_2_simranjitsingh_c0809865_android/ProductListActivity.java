package com.example.lab_1_2_simranjitsingh_c0809865_android;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.lab_1_2_simranjitsingh_c0809865_android.adapter.ProductListAdapter;
import com.example.lab_1_2_simranjitsingh_c0809865_android.databinding.ActivityProductListBinding;
import com.example.lab_1_2_simranjitsingh_c0809865_android.model.Product;
import com.example.lab_1_2_simranjitsingh_c0809865_android.model.ProductViewModel;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {


    private ProductViewModel productViewModel;
    private String TAG = this.getClass().getName();

    private ActivityProductListBinding binding;

    private ArrayList<Product> productsList;

    ProductListAdapter productListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // initializing ViewModel
        productViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication())
                .create(ProductViewModel.class);

        productsList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recP.setLayoutManager(linearLayoutManager);

        productViewModel.getAllProduct().observe(this,products -> {


            productsList.clear();
            productsList.addAll(products);

            productListAdapter = new ProductListAdapter(this, productsList,productViewModel);
            binding.recP.setAdapter(productListAdapter);

        });




    }


}