package br.com.hoffmann.dietadotola.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

import br.com.hoffmann.dietadotola.domain.response.ProfileResponse;

public class ProfileRepository {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String nome;

    public LiveData<ProfileResponse> loadProfile() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String usuarioID = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);

        MutableLiveData<ProfileResponse> profileResponseLiveData = new MutableLiveData<>();
        ProfileResponse response = new ProfileResponse();
        documentReference.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                nome = documentSnapshot.getString("nome");
            }
        }).addOnFailureListener(e -> Log.d("db_error", "Erro ao recuperar os dados do usuário: " + e.getMessage()));

        response.setEmail(auth.getCurrentUser().getEmail());
        response.setNome(auth.getCurrentUser().getDisplayName());
        profileResponseLiveData.setValue(response);
        return profileResponseLiveData;
    }
}
