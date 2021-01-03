package com.example.proyectoandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.proyectoandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
   /* private ActivityMainBinding binding;
    //Home_Fragment2 home_fragment = new Home_Fragment2();
    bottom_home_fragment home_fragment = new bottom_home_fragment();
    bottom_explorar_fragment explorar_fragment = new bottom_explorar_fragment();
    bottom_carrito_fragment carrito_fragment = new bottom_carrito_fragment();
    bottom_favoritos_fragment favoritos_fragment = new bottom_favoritos_fragment();
    bottom_perfil_fragment perfil_fragment = new bottom_perfil_fragment();

*/
    private ActivityMainBinding binding;
    //private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());
        setUpNavigation();

    //  NavController  navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
      //  NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        //Fragment fragment;
        //BottomNavigationView navigationView = binding.bottomNavigation;
        //navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }


    public void setUpNavigation(){
       NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
}


   /* private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.bottom_home_fragment:

                        loadFragment(home_fragment);

                        return true;

                    case R.id.bottom_explorar_fragment:
                        loadFragment(explorar_fragment);
                        return true;

                    case R.id.bottom_carrito_fragment_fragment:
                        loadFragment(carrito_fragment);
                        return true;

                    case R.id.bottom_favoritos_fragment:
                        loadFragment(favoritos_fragment);
                        return true;

                    case R.id.bottom_perfil_fragment_fragment:
                        loadFragment(perfil_fragment);
                        return true;

                }
            return false;
        }

    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
    }
*/
