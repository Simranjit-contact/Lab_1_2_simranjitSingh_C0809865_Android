package com.example.lab_1_2_simranjitsingh_c0809865_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.lab_1_2_simranjitsingh_c0809865_android.Models.ProductViewModel;

import com.example.lab_1_2_simranjitsingh_c0809865_android.databinding.ActivityProductInfoBinding;
import com.example.lab_1_2_simranjitsingh_c0809865_android.Models.Product;


public class ProductInfoActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private String TAG = this.getClass().getName();
    private ActivityProductInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // initializing ViewModel
        productViewModel = new ViewModelProvider.AndroidViewModelFactory(this.getApplication())
                .create(ProductViewModel.class);

        String data = getIntent().getStringExtra("value");
        String id = getIntent().getStringExtra("id");
        Toast.makeText(this, "ID: "+id, Toast.LENGTH_SHORT).show();

        if(data!=null){
            if(data.equals("edit")&&id!=null){
                editProductInfo(Integer.valueOf(id));
            }
        }
        else {
            showProductInfo();
        }

    }

    private void editProductInfo(int id) {
        binding.etPname.setEnabled(true);
        binding.etPdesc.setEnabled(true);
        binding.etPprice.setEnabled(true);
        binding.etLat.setEnabled(true);
        binding.etLon.setEnabled(true);

         productViewModel.getProductById(id).observe(this, products ->{
             binding.etPname.setText(products.get(0).getProductName());
             binding.etPdesc.setText(products.get(0).getProductDesc());
             binding.etPprice.setText(""+products.get(0).getProductPrice());
             binding.etLat.setText(""+products.get(0).getProviderLat());
             binding.etLon.setText(""+products.get(0).getProviderLat());

         });

        binding.btnGetLocation.setVisibility(View.GONE);
        binding.AddProductButton.setText("Update");

        binding.AddProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.etPname.getText().toString().isEmpty()){

                }else if(binding.etPdesc.getText().toString().isEmpty()){
                    Toast.makeText(ProductInfoActivity.this, "Enter The Name First", Toast.LENGTH_SHORT).show();
                }else if(binding.etPdesc.getText().toString().isEmpty()){
                    Toast.makeText(ProductInfoActivity.this, "Enter The Description First", Toast.LENGTH_SHORT).show();
                }else if(binding.etPprice.getText().toString().isEmpty()){
                    Toast.makeText(ProductInfoActivity.this, "Enter The Price First", Toast.LENGTH_SHORT).show();

                }else {

                    productViewModel.update(id,binding.etPname.getText().toString(),binding.etPdesc.getText().toString(),Double.valueOf(binding.etPprice.getText().toString()),
                            Double.valueOf(binding.etLat.getText().toString()),Double.valueOf(binding.etLon.getText().toString()));
                    startActivity(new Intent(ProductInfoActivity.this, ProductListActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            startActivity(new Intent(this, com.example.lab_1_2_simranjitsingh_c0809865_android.ProductListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProductInfo() {


        productViewModel.getFirstProduct().observe(this, products -> {
            if (products.size() != 0) {
                binding.etPname.setEnabled(false);
                binding.etPdesc.setEnabled(false);
                binding.etPprice.setEnabled(false);
                binding.etLat.setEnabled(false);
                binding.etLon.setEnabled(false);
                binding.AddProductButton.setVisibility(View.GONE);
                binding.etPname.setText(products.get(0).getProductName());
                binding.etPdesc.setText(products.get(0).getProductDesc());
                binding.etPprice.setText(""+products.get(0).getProductPrice());
                binding.etLat.setText(""+products.get(0).getProviderLat());
                binding.etLon.setText(""+products.get(0).getProviderLat());
            }else {
                addDemoProducts();
            }
        });
    }

    private void addDemoProducts() {

        Product product0 = new Product();
        product0.setProductName("Hotdog");
        product0.setProductDesc("Hotdog is a popular fast food item, most popular in America. It is similar to a burger in concept with a longer bun and a sausage for filling.");
        product0.setProductPrice(8.00);
        product0.setProviderLat(85.457895621478953);
        product0.setProviderLong(85.487615793487956);

        productViewModel.insert(product0);

        Product product1 = new Product();
        product1.setProductName("Spaghetti");
        product1.setProductDesc("Spaghetti is a dish similar to noodles in shape but made with different ingredients.");
        product1.setProductPrice(6.00);
        product1.setProviderLat(54.457895621478953);
        product1.setProviderLong(54.457895621478953);

        productViewModel.insert(product1);
        Product product2 = new Product();

        product2.setProductName("Noodles");
        product2.setProductDesc("Long noodles made with flour, used in popular dishes like ramen.");
        product2.setProductPrice(10.00);
        product2.setProviderLat(85.457895621478953);
        product2.setProviderLong(85.487615793487956);

        productViewModel.insert(product2);
        Product product3 = new Product();

        product3.setProductName("Jalebi");
        product3.setProductDesc("A sweet confectionary item from india dipped in rose water flavoured sugar syrup");
        product3.setProductPrice(7.00);
        product3.setProviderLat(54.457895621478953);
        product3.setProviderLong(54.457895621478953);

        productViewModel.insert(product3);

        Product product4 = new Product();

        product4.setProductName("Croissant");
        product4.setProductDesc("A very famous french pastry made with butter and multiple layers of dough, enjoyed with coffee.");
        product4.setProductPrice(6.00);
        product4.setProviderLat(85.457895621478953);
        product4.setProviderLong(85.487615793487956);

        productViewModel.insert(product4);

        Product product5 = new Product();

        product5.setProductName("Samosa");
        product5.setProductDesc("A famous street food from India, deep fried in oil with potato filling filled with spices.");
        product5.setProductPrice(8.00);
        product5.setProviderLat(54.457895621478953);
        product5.setProviderLong(54.457895621478953);

        productViewModel.insert(product5);

        Product product6 = new Product();

        product6.setProductName("Naan");
        product6.setProductDesc("A type of bread from India, eaten with vegetables or any thick lentil soup.");
        product6.setProductPrice(2.00);
        product6.setProviderLat(85.457895621478953);
        product6.setProviderLong(85.487615793487956);

        productViewModel.insert(product6);

        Product product7 = new Product();


        product7.setProductName("Stuffed Bread");
        product7.setProductDesc("Bread stuffed with your favorite stuffings.");
        product7.setProductPrice(5.85);
        product7.setProviderLat(54.457895621478953);
        product7.setProviderLong(54.457895621478953);

        productViewModel.insert(product7);

        Product product8 = new Product();

        product8.setProductName("Amritsari Naan");
        product8.setProductDesc("A type of stuffed bread from Punjab, India.  Filled with potatoes and eaten with a chickpea soup.");
        product8.setProductPrice(25.90);
        product8.setProviderLat(85.457895621478953);
        product8.setProviderLong(85.487615793487956);

        productViewModel.insert(product8);

        Product product9 = new Product();

        product9.setProductName("Fruit Cake");
        product9.setProductDesc("A type of cake eaten with tea filled with tiny pieces of fruits.");
        product9.setProductPrice(15.95);
        product9.setProviderLat(85.457895621478953);
        product9.setProviderLong(85.487615793487956);

        productViewModel.insert(product9);

    }
}