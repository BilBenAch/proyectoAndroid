package com.example.proyectoandroid;

import android.app.Application;

import com.example.proyectoandroid.model.Producto;
import com.example.proyectoandroid.modelLogin.AppBaseDeDatos;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class productosRepositorio {
    AppBaseDeDatos.AppDao ProductosDao;
    Executor executor = Executors.newSingleThreadExecutor();

    productosRepositorio(Application application){

        ProductosDao = AppBaseDeDatos.getInstance(application).obtenerDao();

    }

    public void insertar(List<Producto> productos){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(Producto producto : productos) {
                    ProductosDao.insertar(producto);
                }
            }
        });
    }
}
