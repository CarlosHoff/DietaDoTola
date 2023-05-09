package br.com.hoffmann.dietadotola;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private TextView telaCadastro;
    private Button botaoEntrar;
    private EditText emailCadastro, senhaCadastro;
    private ProgressBar progressBar;

    String[] mensagens = {"É necessário preencher todos os campos obrigatórios",
            "Login efetuado com sucesso."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciarComponentes();

        botaoEntrar.setOnClickListener(view -> {
            String email = emailCadastro.getText().toString();
            String senha = senhaCadastro.getText().toString();

            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(Login.this, mensagens[0], Toast.LENGTH_LONG).show();
            } else {
                logarUsuario(email, senha);
            }
        });

        telaCadastro.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Cadastro.class);
            startActivity(intent);
        });
    }

    private void logarUsuario(String email, String senha) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                progressBar.setVisibility(View.VISIBLE);

                new Handler().postDelayed(this::telaPrincipal, 2000);
            } else {
                String erro;
                try {
                    throw Objects.requireNonNull(task.getException());
                } catch (Exception e) {
                    erro = "Erro ao logar o usuario";
                }
                Toast.makeText(Login.this, erro, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void telaPrincipal() {
        Intent intent = new Intent(Login.this, Menu.class);
        startActivity(intent);
        finish();
    }

    private void iniciarComponentes() {
        emailCadastro = findViewById(R.id.email_login);
        senhaCadastro = findViewById(R.id.senha_login);
        botaoEntrar = findViewById(R.id.botao_entrar);
        telaCadastro = findViewById(R.id.texto_cadastro);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            telaPrincipal();
        }
    }
}