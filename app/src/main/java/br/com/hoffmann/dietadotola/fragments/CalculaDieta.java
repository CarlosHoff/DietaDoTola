package br.com.hoffmann.dietadotola.fragments;

import static com.google.android.material.slider.LabelFormatter.LABEL_FLOATING;
import static com.google.android.material.slider.LabelFormatter.LABEL_GONE;
import static com.google.android.material.slider.LabelFormatter.LABEL_VISIBLE;
import static com.google.android.material.slider.LabelFormatter.LABEL_WITHIN_BOUNDS;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.slider.Slider;

import br.com.hoffmann.dietadotola.R;

public class CalculaDieta extends Fragment {

    private Button masc, fem, perdePeso, resultado, ganhaPeso, proximaPagina;
    private int idade, taxaBasalFinal, tdee;
    private double altura, peso;
    private String sexo;
    private TextView resultadoCalculoBasal, objetivoText, numeroIdade, numeroAltura, numeroPeso;
    private MaterialButtonToggleGroup toggleButtonObjetivo, toggleGroup;
    private SeekBar idadeSlider, alturaSlider, pesoSlider;
    private RadioGroup radioGroupAtividade;
    private int atividadeSelecionada;

    public CalculaDieta() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calcula_dieta, container, false);

        iniciaComponentes(view);

        final int white = getResources().getColor(R.color.white);
        final int background = getResources().getColor(R.color.botao);

        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.masculino) {
                    setColor(masc, white, background);
                    setColor(fem, background, white);
                    sexo = "masculino";
                } else {
                    setColor(fem, white, background);
                    setColor(masc, background, white);
                    sexo = "feminino";
                }
            }
        });

        toggleButtonObjetivo.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.emagrecimento) {
                    setColor(perdePeso, white, background);
                    setColor(ganhaPeso, background, white);
                    taxaBasalFinal = tdee - 300;
                    proximaPagina.setVisibility(View.VISIBLE);
                } else {
                    setColor(ganhaPeso, white, background);
                    setColor(perdePeso, background, white);
                    taxaBasalFinal = tdee + 300;
                    proximaPagina.setVisibility(View.VISIBLE);
                }
            }
        });

        proximaPagina.setOnClickListener(v -> {
            Fragment alimentos = new Alimentos();
            Bundle args = new Bundle();
            args.putInt("taxaBasalFinal", taxaBasalFinal);
            alimentos.setArguments(args);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_calcula_dieta, alimentos);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        idadeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numeroIdade.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                idade = (int) seekBar.getProgress();
                numeroIdade.setText(String.valueOf(idade));
            }
        });

        alturaSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numeroAltura.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                altura = (int) seekBar.getProgress();
                numeroAltura.setText(String.valueOf(altura));
            }
        });

        pesoSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numeroPeso.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                peso = (int) seekBar.getProgress();
                numeroPeso.setText(String.valueOf(peso));
            }
        });

        if (radioGroupAtividade.getCheckedRadioButtonId() != -1) {
            resultado.setVisibility(View.VISIBLE);
        }

        atividadeSelecionada = radioGroupAtividade.getCheckedRadioButtonId();
        resultado.setOnClickListener(v -> {
            tdee = calcularTDEE(peso, altura, idade, sexo, atividadeSelecionada);
            resultadoCalculoBasal.setText(String.format("%s Kcal", tdee));
            if (resultadoCalculoBasal.getText() != null) {
                objetivoText.setVisibility(View.VISIBLE);
                toggleButtonObjetivo.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }

    private void iniciaComponentes(View view) {
        toggleGroup = view.findViewById(R.id.toggleButton);
        resultado = view.findViewById(R.id.botao_resultado);
        resultadoCalculoBasal = view.findViewById(R.id.resultado);
        idadeSlider = view.findViewById(R.id.slider_idade);
        alturaSlider = view.findViewById(R.id.slider_altura);
        pesoSlider = view.findViewById(R.id.slider_peso);
        radioGroupAtividade = view.findViewById(R.id.radioGroupAtividade);
        masc = view.findViewById(R.id.masculino);
        fem = view.findViewById(R.id.feminino);
        ganhaPeso = view.findViewById(R.id.ganhoDePeso);
        perdePeso = view.findViewById(R.id.emagrecimento);
        toggleButtonObjetivo = view.findViewById(R.id.toggleButtonObjetivo);
        objetivoText = view.findViewById(R.id.text_objetivo);
        proximaPagina = view.findViewById(R.id.proximaPagina);
        numeroIdade = view.findViewById(R.id.numero_idade);
        numeroAltura = view.findViewById(R.id.numero_altura);
        numeroPeso = view.findViewById(R.id.numero_peso);
    }

    private void setColor(Button button, int bgColor, int textColor) {
        button.setBackgroundColor(bgColor);
        button.setTextColor(textColor);
    }

    public static int calcularTDEE(double peso, double altura, int idade, String sexo, double atividadeFisica) {
        double tdee = 0;
        if (sexo.equalsIgnoreCase("masculino")) {
            tdee = 88.36 + (13.4 * peso) + (4.8 * altura) - (5.7 * idade);
        } else if (sexo.equalsIgnoreCase("feminino")) {
            tdee = 447.6 + (9.2 * peso) + (3.1 * altura) - (4.3 * idade);
        }

        switch ((int) atividadeFisica) {
            case 1:
                tdee *= 1.2;
                break;
            case 2:
                tdee *= 1.375;
                break;
            case 3:
                tdee *= 1.55;
                break;
            case 4:
                tdee *= 1.725;
                break;
            case 5:
                tdee *= 1.9;
                break;
            default:
                Log.d("atividade_fisica", "Atividade física inválida.");
                break;
        }
        return (int) tdee;
    }

}