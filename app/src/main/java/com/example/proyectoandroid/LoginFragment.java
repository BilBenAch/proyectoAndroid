package com.example.proyectoandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.proyectoandroid.databinding.FragmentLoginBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private NavController navController;
    private AutenticacionViewModel autenticacionViewModel;
    //private TextView textView = binding.noEresMiembroRegsitrate;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // su main activity de su login
        //preguntar referente a mi pagina de búsqueda
        //preguntar referente al mensaje de crear una cuenta
        //preguntar como cambiar contraseña
       /* String text = "¿No eres miembro?Registrate";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.GRAY);
        ss.setSpan(fcsRed, 1, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        */


        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autenticacionViewModel = new ViewModelProvider(requireActivity()).get(AutenticacionViewModel.class);
        navController = Navigation.findNavController(view);
        binding.noEresMiembroRegsitrate.setOnClickListener(v ->{
            navController.navigate(R.id.action_loginFragment_to_registrarse);
        } );

        BottomNavigationView navBar = getActivity().findViewById(R.id.bottom_navigation);
        navBar.setVisibility(View.GONE);

        binding.botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.editTextNombreUsuario.getText().toString();
                String password = binding.editPassword.getText().toString();
                autenticacionViewModel.iniciarSesion(username, password);
            }
        });
        autenticacionViewModel.estadoDeLaAutenticacion.observe(getViewLifecycleOwner(), new Observer<AutenticacionViewModel.EstadoDeLaAutenticacion>() {
            @Override
            public void onChanged(AutenticacionViewModel.EstadoDeLaAutenticacion estadoDeLaAutenticacion) {
                switch (estadoDeLaAutenticacion){
                    case AUTENTICADO:
                        navController.navigate(R.id.action_loginFragment_to_bottom_home_fragment2);
                        navBar.setVisibility(View.VISIBLE);
                        break;

                    case AUTENTICACION_INVALIDA:
                        Toast.makeText(getContext(), "CREDENCIALES NO VALIDAS SI NO TIENES UNA CUENTA REGISTRATE", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

}
