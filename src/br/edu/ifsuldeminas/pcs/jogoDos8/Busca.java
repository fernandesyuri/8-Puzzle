package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.Collections;

public class Busca {

    ArrayList<EstadoJogo> filhos;

    public EstadoJogo buscaGulosa(EstadoJogo estado) {
        filhos = estado.gerarFilhos();
        Collections.sort(filhos);

        for (EstadoJogo i : filhos) {

        }

//        for(EstadoJogo i: filhos) {
//            System.out.println(i.jogoEmString());
//            System.out.println(i.getValorHeuristica());
//        }
        return null;
    }
}
