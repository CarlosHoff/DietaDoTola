package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Proteinas implements Parcelable {

    private String nome;
    private int calorias;
    private int qtdProteinas;

    public Proteinas() {
    }

    public Proteinas(String nome, int calorias, int qtdProteinas) {
        this.nome = nome;
        this.calorias = calorias;
        this.qtdProteinas = qtdProteinas;
    }

    protected Proteinas(Parcel in) {
        nome = in.readString();
        calorias = in.readInt();
        qtdProteinas = in.readInt();
    }

    public static final Creator<Proteinas> CREATOR = new Creator<Proteinas>() {
        @Override
        public Proteinas createFromParcel(Parcel in) {
            return new Proteinas(in);
        }

        @Override
        public Proteinas[] newArray(int size) {
            return new Proteinas[size];
        }
    };

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getQtdProteinas() {
        return qtdProteinas;
    }

    public void setQtdProteinas(int qtdProteinas) {
        this.qtdProteinas = qtdProteinas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(calorias);
        dest.writeInt(qtdProteinas);
    }
}
