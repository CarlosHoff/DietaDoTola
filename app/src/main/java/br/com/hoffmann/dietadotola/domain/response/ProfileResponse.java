package br.com.hoffmann.dietadotola.domain.response;

import java.io.Serializable;

public class ProfileResponse implements Serializable {

    private String nome;
    private String email;

    public ProfileResponse() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
