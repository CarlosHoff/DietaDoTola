package br.com.hoffmann.dietadotola;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

public class Cadastro extends AppCompatActivity {

    private Button botaoCadastro;
    private EditText nomeCadastro, emailCadastro, senhaCadastro;
    String[] mensagens = {"É necessário preencher todos os campos obrigatórios",
            "Cadastro realizado com sucesso."};
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        iniciaComponentes();
        auth = FirebaseAuth.getInstance();

        botaoCadastro.setOnClickListener(view -> {

            String nome = nomeCadastro.getText().toString();
            String email = emailCadastro.getText().toString();
            String senha = senhaCadastro.getText().toString();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(Cadastro.this, mensagens[0], Toast.LENGTH_LONG).show();
            } else {
                cadastrarUsuario(nome, email, senha);
            }
        });
    }

    private void cadastrarUsuario(String nome, String email, String senha) {

        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                salvarDadosUsuario(nome);

                Toast.makeText(Cadastro.this, mensagens[1], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Cadastro.this, Login.class);
                startActivity(intent);
            } else {
                String erro;
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (FirebaseAuthWeakPasswordException e) {
                    erro = "Digite uma senha com no minimo 6 caracteres";
                } catch (FirebaseAuthUserCollisionException e) {
                    erro = "Este email ja existe na base de dados";
                } catch (FirebaseAuthInvalidCredentialsException e) {
                    erro = "E-mail invalido";
                } catch (Exception e) {
                    erro = "Falha de comunicação com o Firebase.";
                }
                Toast.makeText(Cadastro.this, erro, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void salvarDadosUsuario(String nome) {
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(nome)
                .build();

        FirebaseUser user = auth.getCurrentUser();
        Objects.requireNonNull(user).updateProfile(profileUpdates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("Nome update", "nome salvo");
            }
        });
    }

    private void iniciaComponentes() {
        botaoCadastro = findViewById(R.id.botao_cadastro);
        nomeCadastro = findViewById(R.id.nome_cadastro);
        emailCadastro = findViewById(R.id.email_cadastro);
        senhaCadastro = findViewById(R.id.senha_cadastro);
    }
}