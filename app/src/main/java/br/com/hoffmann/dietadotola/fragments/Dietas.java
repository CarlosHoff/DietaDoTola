package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import br.com.hoffmann.dietadotola.R;

public class Dietas extends Fragment {


    public Dietas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dietas, container, false);

        //recyclerView = view.findViewById(R.id.recicleViewMinhasApostas);


        return view;
    }


}