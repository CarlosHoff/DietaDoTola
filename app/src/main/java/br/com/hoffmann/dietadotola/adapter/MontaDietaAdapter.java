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

public class MontaDietaAdapter extends RecyclerView.Adapter<MontaDietaAdapter.ViewHolder> {
    private List<CarboidratosCalculados> carboList;
    private List<ProteinasCalculadas> proteinaList;
    private Context context;

    public MontaDietaAdapter(Context context, List<CarboidratosCalculados> carboList, List<ProteinasCalculadas> proteinaList) {
        this.carboList = carboList;
        this.proteinaList = proteinaList;
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

        holder.textoRefeicao.setText(String.format("REFEIÇÃO  %s", position + 1));
        holder.textoCarboidrato.setText(String.format("%s gramas de  %s", carbo.getGramasPorRefeicao(), carbo.getNome()));
        holder.textoProteina.setText(String.format("%s gramas de  %s", proteina.getGramasPorRefeicao(), proteina.getNome()));

    }

    @Override
    public int getItemCount() {
        return carboList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textoRefeicao, textoCarboidrato, textoProteina;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textoRefeicao = itemView.findViewById(R.id.texto_refeicao);
            textoCarboidrato = itemView.findViewById(R.id.texto_carboidrato);
            textoProteina = itemView.findViewById(R.id.texto_proteina);
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

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}