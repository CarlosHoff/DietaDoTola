package br.com.hoffmann.dietadotola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import java.util.Objects;

import br.com.hoffmann.dietadotola.databinding.ActivityMenuBinding;

public class Menu extends AppCompatActivity {

    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initNavigation();
    }

    private void initNavigation(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();

        navController.navigate(R.id.nav_main);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
    }
}