package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    private double altura, peso, grauAtividade, estadoAtual, proteina, carboidratro;
    private String sexo;
    private TextView resultadoCalculoBasal, objetivoText;
    private MaterialButtonToggleGroup toggleButtonObjetivo, toggleGroup;
    private Slider idadeSlider, alturaSlider, pesoSlider, grauAtividadeSlider, estadoAtualSlider;

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
            calculaProteina();
            calculaCarboidrato();
            Fragment alimentos = new Alimentos();
            Bundle args = new Bundle();
            args.putInt("taxaBasalFinal", taxaBasalFinal);
            args.putInt("proteina", (int) proteina);
            args.putInt("carboidratro", (int) carboidratro);
            alimentos.setArguments(args);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_calcula_dieta, alimentos);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        idadeSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                idade = (int) slider.getValue();
            }
        });

        alturaSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                altura = slider.getValue();
            }
        });

        pesoSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                peso = slider.getValue();
            }
        });

        estadoAtualSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                estadoAtual = slider.getValue();
            }
        });

        grauAtividadeSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                grauAtividade = slider.getValue();
            }
        });

        resultado.setOnClickListener(v -> {
            tdee = calcularTDEE(peso, altura, idade, sexo, grauAtividade);
            resultadoCalculoBasal.setText(tdee + " Kcal");
            if (resultadoCalculoBasal.getText() != null) {
                objetivoText.setVisibility(View.VISIBLE);
                toggleButtonObjetivo.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void calculaProteina() {
        switch ((int) estadoAtual) {
            case 1:
                proteina = peso * 2;
                break;
            case 2:
                proteina = peso * 2.6;
                break;
            case 3:
                proteina = peso * 2.2;
                break;
            case 4:
                proteina = peso * 1.8;
                break;
            default:
                Log.d("estado_atual", "Estado atual inválido");
                break;
        }
    }

    private void calculaCarboidrato() {
        switch ((int) estadoAtual) {
            case 1:
                carboidratro = (tdee * 0.55) / 4;
                break;
            case 2:
                carboidratro = (tdee * 0.48) / 4;
                break;
            case 3:
                carboidratro = (tdee * 0.53) / 4;
                break;
            case 4:
                carboidratro = (tdee * 0.58) / 4;
                break;
            default:
                Log.d("switch_Carboidrato", "Erro no metodo calculaCarboidrato.");
                break;
        }
    }

    private void iniciaComponentes(View view) {
        toggleGroup = view.findViewById(R.id.toggleButton);
        resultado = view.findViewById(R.id.botao_resultado);
        resultadoCalculoBasal = view.findViewById(R.id.resultado);
        idadeSlider = view.findViewById(R.id.slider_idade);
        alturaSlider = view.findViewById(R.id.slider_altura);
        pesoSlider = view.findViewById(R.id.slider_peso);
        estadoAtualSlider = view.findViewById(R.id.slider_estado_atual);
        grauAtividadeSlider = view.findViewById(R.id.slider_grau_atividade);
        masc = view.findViewById(R.id.masculino);
        fem = view.findViewById(R.id.feminino);
        ganhaPeso = view.findViewById(R.id.ganhoDePeso);
        perdePeso = view.findViewById(R.id.emagrecimento);
        toggleButtonObjetivo = view.findViewById(R.id.toggleButtonObjetivo);
        objetivoText = view.findViewById(R.id.text_objetivo);
        proximaPagina = view.findViewById(R.id.proximaPagina);
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