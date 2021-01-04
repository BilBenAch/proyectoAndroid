package com.example.proyectoandroid.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
public class Favoritos {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @TypeConverters(ListIntegerConverter.class)
    public List<Integer> productos;
    public int userID;

    public Favoritos(List<Integer> productos, int userID) {
        this.productos = productos;
        this.userID = userID;
    }
}


