package com.example.proyectoandroid.modelLogin;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.example.proyectoandroid.model.Producto;

import java.util.List;

@Database(entities = {Usuario.class, Producto.class}, version = 2, exportSchema = false)
public abstract class AppBaseDeDatos extends RoomDatabase {

    public abstract AppDao obtenerDao();

    private static volatile AppBaseDeDatos INSTANCE;

    public static AppBaseDeDatos getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (AppBaseDeDatos.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppBaseDeDatos.class, "app.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    @Dao
    public interface AppDao {
        @Insert
        void insertarUsuario(Usuario usuario);
        @Update
        void updateContrasenia(Usuario usuario);

        @Query("SELECT * FROM Usuario WHERE username = :nombre AND password = :contrasenya")
        Usuario autenticar(String nombre, String contrasenya);

        @Query("SELECT * FROM Usuario WHERE username = :nombre")
        Usuario comprobarNombreDisponible(String nombre);

        @Query("SELECT * FROM Usuario WHERE username = :nombre AND email = :email AND password = :password")
        Usuario comprobarContraseniaCorrecta(String nombre, String email, String password);

        //Producto
        @Insert
        void insertar(Producto producto);

        @Query("SELECT * FROM Producto")
        LiveData<List<Producto>> obtener();

        @Query("SELECT * FROM Producto WHERE nombre LIKE '%' || :t || '%'")
        LiveData<List<Producto>> buscar(String t);
    }
}
