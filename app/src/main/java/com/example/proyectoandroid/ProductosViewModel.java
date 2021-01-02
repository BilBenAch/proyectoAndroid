package com.example.proyectoandroid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

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

}
