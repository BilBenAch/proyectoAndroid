package com.example.proyectoandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.proyectoandroid.databinding.FragmentRegistrarseBinding;


public class RegistrarseFragment extends Fragment {


    private FragmentRegistrarseBinding binding;
    private AutenticacionViewModel autenticacionViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRegistrarseBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        autenticacionViewModel = new ViewModelProvider(requireActivity()).get(AutenticacionViewModel.class);
        navController = Navigation.findNavController(view);
        autenticacionViewModel.iniciarRegistro();

        binding.botonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.crearCuentaNombreUsuario.getText().toString();
                String email = binding.crearCrearCuentaEmail.getText().toString();
                String password = binding.crearCuentaContrasenia1.getText().toString();
                String password2 = binding.crearCuentaContrasenia2.getText().toString();

                boolean error = false;

                if(username.isEmpty()){
                    binding.crearCuentaNombreUsuario.setError("El campo nombre de usuario no puede estar vacío ");
                    error = true;
                }
                if(email.isEmpty()){
                    binding.crearCrearCuentaEmail.setError("El campo email no puede estar vacío ");
                    error = true;
                }
                if(password.isEmpty()){
                    binding.crearCuentaContrasenia1.setError("El campo password no puede estar vacío ");
                    error = true;
                }
                if(password2.isEmpty()){
                    binding.crearCuentaContrasenia2.setError("El campo password no puede estar vacío ");
                    error = true;
                }
                if(password.length() < 8){
                    binding.crearCuentaContrasenia1.setError("Password menor a 8");
                    error = true;
                }
                if(!password.equals(password2)){
                    binding.crearCuentaContrasenia2.setError("Passwords no cinciden");
                    error = true;
                }

                if(!error) {
                    autenticacionViewModel.crearCuenta(username, email, password, password2);
                }
            }
        });
        //preguntar al gerard porque entra de nuevo al crearcuenta
        autenticacionViewModel.estadoDelRegistro.observe(getViewLifecycleOwner(), new Observer<AutenticacionViewModel.EstadoDelRegistro>() {
            @Override
            public void onChanged(AutenticacionViewModel.EstadoDelRegistro estadoDelRegistro) {
                switch (estadoDelRegistro){
                    case NOMBRE_NO_DISPONIBLE:
                       //Toast.makeText(getContext(), "NOMBRE DE USUARIO NO DISPONIBLE", Toast.LENGTH_SHORT).show();
                        binding.crearCuentaNombreUsuario.setError("Nombre de usuario no disponible");
                        break;
                    case REGISTRO_COMPLETADO:
                        navController.navigate(R.id.action_registrarse_to_cuentaCreada);
                        break;
                }
            }
        });
    }
}