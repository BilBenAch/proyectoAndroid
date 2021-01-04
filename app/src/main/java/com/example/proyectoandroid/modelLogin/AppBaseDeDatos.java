package com.example.proyectoandroid.modelLogin;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import com.example.proyectoandroid.model.Favoritos;
import com.example.proyectoandroid.model.Producto;

import java.util.List;

@Database(entities = {Usuario.class, Producto.class, Favoritos.class}, version = 4, exportSchema = false)
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

        @Query("SELECT * FROM Usuario WHERE id =:userID")
        Usuario getUser(int userID);

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


        @Query("SELECT * FROM Producto WHERE id IN (:ids)")
        LiveData<List<Producto>> obtenerListadoProductos(List<Integer> ids);


        @Query("SELECT * FROM Producto WHERE nombre LIKE '%' || :t || '%'")
        LiveData<List<Producto>> buscar(String t);


        //Favoritos
        @Insert
        public void anadirFavoritos(Favoritos favorito);

        @Query("select * from favoritos WHERE userID = :id")
        LiveData<List<Favoritos>> obtenerFavoritos(int id);

        @Query("SELECT EXISTS (SELECT 1 FROM favoritos WHERE productos LIKE '%' || :productId || '%' AND userID = :userID)")
         int esFavorito(int userID, int productId);

        @Delete
        public void delete(Favoritos favoritosList);
    }
}
