package br.com.hoffmann.dietadotola.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import br.com.hoffmann.dietadotola.domain.response.ProfileResponse;
import br.com.hoffmann.dietadotola.repository.ProfileRepository;

public class PerfilViewModel extends ViewModel {

    private final ProfileRepository profileRepository;

    public PerfilViewModel() {
        profileRepository = new ProfileRepository();
    }

    public LiveData<ProfileResponse> loadProfile() {
        return profileRepository.loadProfile();
    }
}
