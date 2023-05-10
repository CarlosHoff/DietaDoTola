package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.domain.response.ProfileResponse;
import br.com.hoffmann.dietadotola.viewmodel.PerfilViewModel;

public class Profile extends Fragment {

    private TextView nameScreen, emailScreen;

    public Profile() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        startComponents(view);

        PerfilViewModel perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);
        perfilViewModel.loadProfile().observe(getViewLifecycleOwner(), profileResponse -> {
            if (profileResponse != null) {
                ProfileResponse user = new ProfileResponse();
                user.setNome(Objects.requireNonNull(profileResponse.getNome()));
                user.setEmail(Objects.requireNonNull(profileResponse.getEmail()));
                fillScreen(user);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void startComponents(View view) {
        nameScreen = view.findViewById(R.id.nome_perfil);
        emailScreen = view.findViewById(R.id.email_perfil);
        String teste;
    }

    private void fillScreen(ProfileResponse profileResponse) {
        nameScreen.setText(profileResponse.getNome());
        emailScreen.setText(profileResponse.getEmail());
    }

}