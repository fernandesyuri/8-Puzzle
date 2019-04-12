package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.Collections;

public class Busca {

    ArrayList<EstadoJogo> nosAbertos;
    ArrayList<EstadoJogo> nosFechados;

    public EstadoJogo buscaGulosa(EstadoJogo estado, EstadoJogo objetivo) {
        this.nosFechados = new ArrayList<>();
        this.nosAbertos = new ArrayList<>();
        this.nosAbertos.add(estado);

        while (true) {
            EstadoJogo estadoAtual = nosAbertos.remove(0);
            if (estadoAtual.jogoEmString().equals(objetivo.jogoEmString())) {
                return estadoAtual;
            } else {
                nosAbertos.addAll(estadoAtual.gerarFilhos());
                Collections.sort(this.nosAbertos);
                nosFechados.add(estadoAtual);
            }
        }
    }
}
