package com.example.proyectoandroid;

import android.app.Application;

import androidx.lifecycle.LiveData;

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

    public LiveData<List<Producto>> obtener() {
        return ProductosDao.obtener();
    }
    public LiveData<List<Producto>> obtenerProductosFavoritos(List<Integer> favoritosId){
        return ProductosDao.obtenerListadoProductos(favoritosId);
    }

    public int esFavorito(Integer usuarioId, Integer productoID){
        return ProductosDao.esFavorito(usuarioId, productoID);
    }
}
