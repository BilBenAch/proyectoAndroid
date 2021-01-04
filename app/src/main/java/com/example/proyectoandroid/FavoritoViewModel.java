package com.example.proyectoandroid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.proyectoandroid.model.Favoritos;
import com.example.proyectoandroid.model.Producto;

import java.util.List;

public class FavoritoViewModel extends AndroidViewModel {
    FavoritoRepositorio favoritoRepositorio;
    public FavoritoViewModel(@NonNull Application application) {
        super(application);
        favoritoRepositorio = new FavoritoRepositorio(application);
    }


    public void insertarProducto(Favoritos favorito) {
        favoritoRepositorio.anadirFavorito(favorito);
    }

    public LiveData<List<Favoritos>> obtenerFavoritos(Integer usuarioId) {
        return favoritoRepositorio.obtenerFavoritos(usuarioId);
    }
    public LiveData<List<Producto>> obtenerProductosFavoritos(List<Integer> favoritosId){
        return favoritoRepositorio.obtenerProductosFavoritos(favoritosId);
    }

}
