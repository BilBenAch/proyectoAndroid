package com.example.proyectoandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.proyectoandroid.databinding.FragmentBottomHomeBinding;

public class bottom_home_fragment extends Fragment {
    private FragmentBottomHomeBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentBottomHomeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);
        binding.botonComprarAbrigoHombre.setOnClickListener(v -> {
            navController.navigate(R.id.action_global_productosFragment);

        });
        binding.botonAnadirProductos.setOnClickListener(v -> {
            navController.navigate(R.id.action_global_anadirProductoFragment);

        });
    }
}