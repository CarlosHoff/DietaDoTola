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
import br.com.hoffmann.dietadotola.domain.response.auxiliar.CarboidratosCalculados;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.ProteinasCalculadas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.VegetaisCalculados;

public class MontaDietaAdapter extends RecyclerView.Adapter<MontaDietaAdapter.ViewHolder> {
    private List<CarboidratosCalculados> carboList;
    private List<ProteinasCalculadas> proteinaList;
    private List<VegetaisCalculados> vegetaisList;
    private Context context;

    public MontaDietaAdapter(Context context, List<CarboidratosCalculados> carboList, List<ProteinasCalculadas> proteinaList, List<VegetaisCalculados> vegetaisList) {
        this.carboList = carboList;
        this.proteinaList = proteinaList;
        this.vegetaisList = vegetaisList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(context).inflate(R.layout.lista_dietas, parent, false);
        return new ViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarboidratosCalculados carbo = carboList.get(position);
        ProteinasCalculadas proteina = proteinaList.get(position);
        VegetaisCalculados vegetal = vegetaisList.get(position);

        holder.textoRefeicao.setText(String.format("REFEIÇÃO %s", position + 1));
        holder.textoUm.setText(String.format("%s gramas de  %s", carbo.getGramasPorRefeicao(), carbo.getNome()));
        holder.textoDois.setText(String.format("%s gramas de  %s", proteina.getGramasPorRefeicao(), proteina.getNome()));

    }


    @Override
    public int getItemCount() {
        return carboList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textoRefeicao, textoUm, textoDois;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textoRefeicao = itemView.findViewById(R.id.texto_refeicao);
            textoUm = itemView.findViewById(R.id.texto_um);
            textoDois = itemView.findViewById(R.id.texto_dois);
        }
    }

    public List<CarboidratosCalculados> getCarboList() {
        return carboList;
    }

    public void setCarboList(List<CarboidratosCalculados> carboList) {
        this.carboList = carboList;
    }

    public List<ProteinasCalculadas> getProteinaList() {
        return proteinaList;
    }

    public void setProteinaList(List<ProteinasCalculadas> proteinaList) {
        this.proteinaList = proteinaList;
    }

    public List<VegetaisCalculados> getVegetaisList() {
        return vegetaisList;
    }

    public void setVegetaisList(List<VegetaisCalculados> vegetaisList) {
        this.vegetaisList = vegetaisList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}