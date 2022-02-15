package com.example.lab_1_2_simranjitsingh_c0809865_android.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_1_2_simranjitsingh_c0809865_android.ProductInfoActivity;
import com.example.lab_1_2_simranjitsingh_c0809865_android.R;
import com.example.lab_1_2_simranjitsingh_c0809865_android.databinding.ActivityProductListBinding;
import com.example.lab_1_2_simranjitsingh_c0809865_android.model.Product;
import com.example.lab_1_2_simranjitsingh_c0809865_android.model.ProductViewModel;

import java.util.ArrayList;

public class ProductListAdapter<T> extends RecyclerView.Adapter<ProductListAdapter<T>.ViewHolder> {

    private ArrayList<Product> productsList;
    private Activity context;
    private ActivityProductListBinding binding;
    private ProductViewModel productViewModel;

    public ProductListAdapter(Activity context, ArrayList<Product> list, ProductViewModel productViewModel){
        this.productsList = list;
        this.context = context;
        this.productViewModel = productViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.title.setText(productsList.get(position).getProductName());
        holder.price.setText("$ "+productsList.get(position).getProductPrice());
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ProductInfoActivity.class);
                i.putExtra("value","edit" );
                i.putExtra("id",String.valueOf(productsList.get(position).getId()) );
                context.startActivity(i);
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productViewModel.delete_by_id((int) productsList.get(position).getId())  ;
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, price;
        ImageView btn_edit, btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            btn_edit = itemView.findViewById(R.id.edit_btn);
            btn_delete = itemView.findViewById(R.id.delete_btn);
        }
    }
    @Override
    public int getItemCount() {
        return productsList.size() ;
    }
}