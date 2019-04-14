package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

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

    public EstadoJogo Aestrela(EstadoJogo estadoInicial) {

        HashSet<String> estadosJaVisitados = new HashSet<>();
        int qtdEstadosVisitados = 0;
        estadoInicial.setValorTotal(0);

        PriorityQueue<EstadoJogo> filaDeEstados = new PriorityQueue<>(); // Fila de estados a serem expandidos, por ordem de prioridade

        EstadoJogo estadoAtual = estadoInicial;

        while (estadoAtual != null && !estadoAtual.ehEstadoFinal()) {

            estadosJaVisitados.add(estadoAtual.jogoEmString());
            estadoAtual.gerarFilhos(estadosJaVisitados);

            for (EstadoJogo filho : estadoAtual.getFilhos()) {
                filho.setValorTotal(estadoAtual.getValorTotal() + filho.calcularHeuristicaManhattan());
                filaDeEstados.add(filho);
            }

            estadoAtual = filaDeEstados.poll();
            qtdEstadosVisitados += 1;
        }

        if (estadoAtual == null) {
            System.out.println("Impossível encontrar uma solução.");
            return null;

        } else {
            System.out.println("Solução A* encontrada -> " + estadoAtual.jogoEmString());
            System.out.println("Quantidade de jogadas necessárias da raiz até a solução -> " + estadoAtual.getNivel());
            System.out.println("Quantidade de nós visitados -> " + qtdEstadosVisitados);
            System.out.println("Custo total da solução -> " + estadoAtual.getValorTotal());
            return estadoAtual;
        }
    }

    public static void main(String[] args) {

        // 431760825
        Integer[][] jogoInicial = new Integer[3][3];
        jogoInicial[0][0] = null;
        jogoInicial[0][1] = 2;
        jogoInicial[0][2] = 3;
        jogoInicial[1][0] = 1;
        jogoInicial[1][1] = 4;
        jogoInicial[1][2] = 5;
        jogoInicial[2][0] = 8;
        jogoInicial[2][1] = 7;
        jogoInicial[2][2] = 6;

        EstadoJogo raiz = new EstadoJogo(jogoInicial);
        //raiz.gerarEstadoInicial();

        System.out.println("Estado inicial -> " + raiz.jogoEmString());

        Busca busca = new Busca();
        EstadoJogo solucao = busca.Aestrela(raiz);
    }
}
