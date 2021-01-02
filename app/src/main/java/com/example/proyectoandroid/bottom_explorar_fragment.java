package com.example.proyectoandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.proyectoandroid.databinding.FragmentBottomExplorarBinding;


public class bottom_explorar_fragment extends Fragment {
    FragmentBottomExplorarBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentBottomExplorarBinding.inflate(inflater, container, false)).getRoot();
    }
}