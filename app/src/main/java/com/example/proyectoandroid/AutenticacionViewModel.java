package com.example.proyectoandroid;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.proyectoandroid.modelLogin.AutenticacionManager;
import com.example.proyectoandroid.modelLogin.Usuario;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AutenticacionViewModel extends AndroidViewModel {
    Executor executor = Executors.newSingleThreadExecutor();

    enum EstadoDeLaAutenticacion {
        NO_AUTENTICADO,
        AUTENTICADO,
        AUTENTICACION_INVALIDA
    }

    enum EstadoDelRegistro {
        INICIO_DEL_REGISTRO,
        NOMBRE_NO_DISPONIBLE,
        REGISTRO_COMPLETADO
    }

    enum EstadoDelCambioDePassword {
        NOMBRE_INCORRECTO,
        CONTRASENIA_INCORRECTA,
        EMAIL_INCORRECTO,
        EMAIL_USUARIO_INCORRECTO,
        CONTRASENIA_CAMBIADA
    }

    MutableLiveData<EstadoDeLaAutenticacion> estadoDeLaAutenticacion = new MutableLiveData<>(EstadoDeLaAutenticacion.NO_AUTENTICADO);
    MutableLiveData<Usuario> usuarioAutenticado = new MutableLiveData<>();
    MutableLiveData<EstadoDelRegistro> estadoDelRegistro = new MutableLiveData<>(EstadoDelRegistro.INICIO_DEL_REGISTRO);
    MutableLiveData<EstadoDelRegistro> usuarioRegistrado = new MutableLiveData<>();

    //cambio contrasenia
    MutableLiveData<EstadoDelCambioDePassword> estadoUsuarioContrasenia = new MutableLiveData<>();


    AutenticacionManager autenticacionManager;

    public AutenticacionViewModel(@NonNull Application application) {
        super(application);
        autenticacionManager = new AutenticacionManager(application);
    }


    void iniciarSesion(String username, String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                autenticacionManager.iniciarSesion(username, password, new AutenticacionManager.IniciarSesionCallback() {

                    @Override

                    public void cuandoUsuarioAutenticado(Usuario usuario) {
                        usuarioAutenticado.postValue(usuario);
                        estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICADO);
                    }

                    @Override
                    public void cuandoAutenticacionNoValida() {
                        estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.AUTENTICACION_INVALIDA);
                    }
                });
            }
        });
    }

    void iniciarRegistro() {
        estadoDelRegistro.postValue(EstadoDelRegistro.INICIO_DEL_REGISTRO);

    }

    //Seguir aqui
    void crearCuenta(String username, String email, String password, String password2) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                autenticacionManager.crearCuenta(username, email, password, password2, new AutenticacionManager.RegistrarCallback() {
                    @Override
                    public void cuandoRegistroCompletado() {
                        if (estadoDelRegistro.equals(EstadoDelRegistro.REGISTRO_COMPLETADO)) {
                            estadoDelRegistro.postValue(EstadoDelRegistro.INICIO_DEL_REGISTRO);
                        } else {
                            estadoDelRegistro.postValue(EstadoDelRegistro.REGISTRO_COMPLETADO);
                        }
                    }

                    @Override
                    public void cuandoNombreNoDisponible() {
                        estadoDelRegistro.postValue(EstadoDelRegistro.NOMBRE_NO_DISPONIBLE);
                    }
                });
            }
        });
    }


    void cerrarSesion() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                usuarioAutenticado.postValue(null);
                estadoDeLaAutenticacion.postValue(EstadoDeLaAutenticacion.NO_AUTENTICADO);
            }
        });
    }

    //seguir aqui
    void actualizarPassword(String usuario, String email, String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                autenticacionManager.cambiarContrasenia(usuario, email, password, new AutenticacionManager.CambiarContraseniaCallback() {

                    @Override
                    public void cuandoContraseniaCambiada() {
                        estadoUsuarioContrasenia.postValue(EstadoDelCambioDePassword.CONTRASENIA_CAMBIADA);
                        actualizarPassword(usuario, email, password);
                    }

                    @Override
                    public void cuandoEmailYUsuarioNoCoinciden() {
                        estadoUsuarioContrasenia.postValue(EstadoDelCambioDePassword.EMAIL_USUARIO_INCORRECTO);
                    }

                    @Override
                    public void cuandoUsuariooEsVacio() {
                        estadoUsuarioContrasenia.postValue(EstadoDelCambioDePassword.NOMBRE_INCORRECTO);
                    }

                    @Override
                    public void cuandoEmailEsVacio() {
                        estadoUsuarioContrasenia.postValue(EstadoDelCambioDePassword.EMAIL_INCORRECTO);
                    }

                    @Override
                    public void cuandoConstraseniaEsVacio() {
                        estadoUsuarioContrasenia.postValue(EstadoDelCambioDePassword.EMAIL_INCORRECTO);
                    }
                });
            }
        });
    }
}
