package com.example.proyectoandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectoandroid.databinding.FragmentProductosBinding;
import com.example.proyectoandroid.databinding.ViewholderProductoBinding;
import com.example.proyectoandroid.model.Favoritos;
import com.example.proyectoandroid.model.Producto;
import com.example.proyectoandroid.modelLogin.Usuario;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class productosFragment extends Fragment {
    private FragmentProductosBinding binding;
    private ProductosViewModel productosViewModel;
    private AutenticacionViewModel autenticacionViewModel;
    private FavoritoViewModel favoritoViewModel;

    Usuario usuario;

    Executor executor = Executors.newSingleThreadExecutor();
    Runnable runOnUiThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return (binding = FragmentProductosBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autenticacionViewModel = new ViewModelProvider(requireActivity()).get(AutenticacionViewModel.class);
        productosViewModel = new ViewModelProvider(requireActivity()).get(ProductosViewModel.class);
        ProductosAdapter productosAdapter = new ProductosAdapter();
        binding.listaProductos.setAdapter(productosAdapter);
        favoritoViewModel = new ViewModelProvider(requireActivity()).get(FavoritoViewModel.class);
        //lista de productos
        productosViewModel.obtener().observe(getViewLifecycleOwner(), productosList -> {
            productosAdapter.establecerProductosList(productosList);
        });

        //te devuelve el usuario actual
        usuario = autenticacionViewModel.usuarioAutenticado.getValue();

        /*List<Integer> idProductos;
        idProductos = new ArrayList<>();

        productosViewModel.obtener().observe(getViewLifecycleOwner(), productosList -> {
            productosAdapter.establecerProductosList(productosList);
        });

         */

        //seguir aqui
        /*favoritoViewModel.obtenerFavoritos(usuario.getId()).observe(getViewLifecycleOwner(), productosList -> {

            for (Favoritos favoritos : productosList
            ) {
                idProductos.add(favoritos.productos.get(0));
                productosAdapter.establecerFavoritos(productosList);
            }

        });

         */
        // favoritoViewModel.obtenerProductosFavoritos(idProductos);
        //System.out.println("para");
        //esto es nuevo
        //favoritoViewModel.obtenerProductosFavoritos(idProductos);
        //System.out.println("Para");
    }


    class ProductosAdapter extends RecyclerView.Adapter<ProductosViewHolder> {
        List<Producto> productoList;
        List<Favoritos> favoritosList;
        bottom_favoritos_fragment bottom_favoritos_fragment;

        @NonNull
        @Override
        public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductosViewHolder(ViewholderProductoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
            Producto producto = productoList.get(position);
            holder.binding.nombre.setText(producto.nombre);
            Glide.with(holder.itemView).load(producto.imagenes.get(0)).into(holder.binding.imagen);

            //peta
            executor.execute(new Runnable() {
                @Override
                public void run() {

                    if(productosViewModel.esFavorito(usuario.getId(), producto.getId()) == 1){
                        holder.binding.favoritosProductos.setColorFilter(ActivityCompat.getColor(getContext(), android.R.color.holo_red_light));
                    }
                    else {
                        holder.binding.favoritosProductos.setImageResource(R.drawable.logofavoritos);
                    }
                   // int num = productosViewModel.productosRepositorio.ProductosDao.esFavorito(usuario.getId(), producto.getId());
                   // if (productosViewModel. productosRepositorio.ProductosDao.esFavorito(usuario.getId(), producto.getId()) == 1) {
                     //   holder.binding.favoritosProductos.setImageResource(R.drawable.logofavoritos);

                    //} else
                    //holder.binding.favoritosProductos.setColorFilter(ActivityCompat.getColor(getContext(), android.R.color.holo_red_light));
                    //System.out.println("Para aqui");
                }

            });

            //peta
            holder.binding.favoritosProductos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    executor.execute((new Runnable() {
                        @Override
                        public void run() {
                            //Favoritos favoritos = new Favoritos();
                            if (productosViewModel.esFavorito(usuario.getId(), producto.getId())  != 1) {
                                holder.binding.favoritosProductos.setColorFilter(ActivityCompat.getColor(getContext(), android.R.color.holo_red_light));
                                favoritoViewModel.insertarProducto(new Favoritos(Arrays.asList(producto.id), usuario.getId()));
                                Toast.makeText(getContext(), "Producto añadido a favoritos", Toast.LENGTH_SHORT).show();
                            } else {
                                favoritoViewModel.favoritoRepositorio.FavoritosDao.delete(new Favoritos(Arrays.asList(producto.id), usuario.getId()));
                                holder.binding.favoritosProductos.setImageResource(R.drawable.logofavoritos);
                                //delete(Favoritos favoritosList);

                            }
                            //favoritoViewModel.insertarProducto(new Favoritos(Arrays.asList(producto.id), usuario.getId()));
                            //holder.binding.favoritosProductos.setColorFilter(ActivityCompat.getColor(getContext(), android.R.color.holo_green_dark));
                            //holder.binding.favoritosProductos.setImageResource(R.drawable.logofavoritos);
                        }

                    }));
                }

            });

        }

        @Override
        public int getItemCount() {
            return productoList == null ? 0 : productoList.size();
        }

        void establecerProductosList(List<Producto> productoList) {
            this.productoList = productoList;
            notifyDataSetChanged();
        }

    }

    class ProductosViewHolder extends RecyclerView.ViewHolder {
        ViewholderProductoBinding binding;

        public ProductosViewHolder(@NonNull ViewholderProductoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}