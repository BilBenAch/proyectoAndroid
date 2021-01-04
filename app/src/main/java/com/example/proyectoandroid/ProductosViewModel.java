package com.example.proyectoandroid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectoandroid.model.Producto;

import java.util.List;

public class ProductosViewModel extends AndroidViewModel {
    productosRepositorio productosRepositorio;

    public ProductosViewModel(@NonNull Application application) {
        super(application);

        productosRepositorio = new productosRepositorio(application);
    }

    public void insertar(List<Producto> productos) {
        productosRepositorio.insertar(productos);
    }

    public LiveData<List<Producto>> obtener() {
        return productosRepositorio.obtener();
    }

    public LiveData<List<Producto>> obtenerProductosFavoritos(List<Integer> favoritosId){
        return productosRepositorio.obtenerProductosFavoritos(favoritosId);
    }

    public int esFavorito(Integer usuarioId, Integer productoID){
        return productosRepositorio.esFavorito(usuarioId, productoID);
    }
}
