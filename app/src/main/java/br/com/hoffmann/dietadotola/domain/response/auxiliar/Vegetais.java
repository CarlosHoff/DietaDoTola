package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import android.os.Parcel;
import android.os.Parcelable;

public class Vegetais implements Parcelable {

    private String nome;
    private int calorias;
    private int qtdCarboidratos;
    private int qtdProteinas;

    public Vegetais() {
    }

    public Vegetais(String nome, int calorias, int qtdCarboidratos, int qtdProteinas) {
        this.nome = nome;
        this.calorias = calorias;
        this.qtdCarboidratos = qtdCarboidratos;
        this.qtdProteinas = qtdProteinas;
    }

    protected Vegetais(Parcel in) {
        nome = in.readString();
        calorias = in.readInt();
        qtdCarboidratos = in.readInt();
        qtdProteinas = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(calorias);
        dest.writeInt(qtdCarboidratos);
        dest.writeInt(qtdProteinas);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Vegetais> CREATOR = new Creator<Vegetais>() {
        @Override
        public Vegetais createFromParcel(Parcel in) {
            return new Vegetais(in);
        }

        @Override
        public Vegetais[] newArray(int size) {
            return new Vegetais[size];
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

    public int getQtdCarboidratos() {
        return qtdCarboidratos;
    }

    public void setQtdCarboidratos(int qtdCarboidratos) {
        this.qtdCarboidratos = qtdCarboidratos;
    }

    public int getQtdProteinas() {
        return qtdProteinas;
    }

    public void setQtdProteinas(int qtdProteinas) {
        this.qtdProteinas = qtdProteinas;
    }
}
