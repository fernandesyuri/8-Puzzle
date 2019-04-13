package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.Collections;

public class Busca {

    ArrayList<EstadoJogo> nosParaExpansao;
    ArrayList<EstadoJogo> nosJaExpandidos;

    public EstadoJogo buscaGulosa(EstadoJogo estado, EstadoJogo objetivo) {
        this.nosJaExpandidos = new ArrayList<>();
        this.nosParaExpansao = new ArrayList<>();
        this.nosParaExpansao.add(estado);

        while (true) {
            EstadoJogo estadoAtual = nosParaExpansao.remove(0);
           
            if (estadoAtual.jogoEmString().equals(objetivo.jogoEmString())) { 
                System.out.println("achei resultado ");
                estadoAtual.imprimeEstado();
                return estadoAtual;
            } else { 
                nosParaExpansao.addAll(estadoAtual.gerarFilhos());
                Collections.sort(this.nosParaExpansao);
                nosJaExpandidos.add(estadoAtual);
            }
        }
    }
}
