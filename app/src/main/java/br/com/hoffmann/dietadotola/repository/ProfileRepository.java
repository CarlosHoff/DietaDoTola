package br.com.hoffmann.dietadotola.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import br.com.hoffmann.dietadotola.domain.response.ProfileResponse;

public class ProfileRepository {

    private String nomePerfil;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;

    public LiveData<ProfileResponse> loadProfile() {

        usuarioID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener((documentSnapshot, error) -> {
            if (documentSnapshot != null) {
                nomePerfil = documentSnapshot.getString("nome");
            }
        });
        MutableLiveData<ProfileResponse> profileResponseLiveData = new MutableLiveData<>();
        ProfileResponse response = new ProfileResponse();
        response.setNome(nomePerfil);
        response.setEmail(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail());
        profileResponseLiveData.setValue(response);

        return profileResponseLiveData;
    }
}
