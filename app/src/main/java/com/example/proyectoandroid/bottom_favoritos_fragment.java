package com.example.proyectoandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyectoandroid.databinding.FragmentBottomFavoritosBinding;
import com.example.proyectoandroid.modelLogin.Usuario;


public class bottom_favoritos_fragment extends Fragment {
    private FavoritoViewModel favoritoViewModel;
    private FragmentBottomFavoritosBinding binding;
    private Usuario usuario;
    AutenticacionViewModel autenticacionViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentBottomFavoritosBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


       /* usuario = autenticacionViewModel.usuarioAutenticado.getValue();

        favoritoViewModel = new ViewModelProvider(requireActivity()).get(FavoritoViewModel.class);

        List<Integer> idProductos = new ArrayList<>();

        favoritoViewModel.obtenerFavoritos(usuario.getId()).observe(getViewLifecycleOwner(), productosList -> {
            for (Favoritos favoritos : productosList
                 ) {
                idProductos.add(favoritos.productos.get(0));
            }
            System.out.println("Para");
        });






        class FavoritosAdapter extends RecyclerView.Adapter<productosFragment.ProductosViewHolder> {
            List<Favoritos> favoritosListList;


            @NonNull
            @Override
            public productosFragment.ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull productosFragment.ProductosViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        }*/
    }
}