package com.example.lab_1_2_simranjitsingh_c0809865_android.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.lab_1_2_simranjitsingh_c0809865_android.DataFiles.ProductDao;
import com.example.lab_1_2_simranjitsingh_c0809865_android.Models.Product;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class ProductRoomDb extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile ProductRoomDb INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ProductRoomDb getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductRoomDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProductRoomDb.class,
                            "product_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback sRoomDatabaseCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    databaseWriteExecutor.execute(() -> {
                        ProductDao productDao = INSTANCE.productDao();
                        productDao.deleteAll();

                    });
                }
            };
}
