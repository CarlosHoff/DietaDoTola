package br.com.hoffmann.dietadotola.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.FrutasCalculadas;

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.ViewHolder> {

    private List<FrutasCalculadas> frutasList;
    private Context context;

    public FrutaAdapter(Context context, List<FrutasCalculadas> frutasList) {
        this.frutasList = frutasList;
        this.context = context;
    }

    @NonNull
    @Override
    public FrutaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.cardview_frutas, parent, false);
        return new ViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0 && frutasList.size() > 1) {
            holder.frutaUm.setText(String.format("%s gramas de  %s", 200, frutasList.get(0).getNome()));
            holder.frutaDois.setText(String.format("%s gramas de  %s", 100, frutasList.get(1).getNome()));
        } else if (position == 0 && frutasList.size() == 1) {
            FrutasCalculadas frutasUm = frutasList.get(0);
            holder.frutaUm.setText(String.format("%s gramas de  %s", 300, frutasUm.getNome()));
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView frutaUm, frutaDois;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            frutaUm = itemView.findViewById(R.id.texto_fruta_um);
            frutaDois = itemView.findViewById(R.id.texto_fruta_dois);
        }
    }

    public List<FrutasCalculadas> getFrutasList() {
        return frutasList;
    }

    public void setFrutasList(List<FrutasCalculadas> frutasList) {
        this.frutasList = frutasList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
