package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Busca {

    ArrayList<EstadoJogo> nosParaExpansao;
    ArrayList<EstadoJogo> nosJaExpandidos;

    public ArrayList<EstadoJogo> buscaGulosa(EstadoJogo estadoInicial) {

        HashSet<String> estadosJaVisitados = new HashSet<>();
        int qtdEstadosVisitados = 0;

        PriorityQueue<EstadoJogo> filaDeEstados = new PriorityQueue<>(); // Fila de estados a serem expandidos, por ordem de prioridade

        EstadoJogo estadoAtual = estadoInicial;

        while (estadoAtual != null && !estadoAtual.ehEstadoFinal()) {

            estadosJaVisitados.add(estadoAtual.jogoEmString());
            estadoAtual.gerarFilhos(estadosJaVisitados);

            for (EstadoJogo filho : estadoAtual.getFilhos()) {
                filaDeEstados.add(filho);
            }

            estadoAtual = filaDeEstados.poll();
            qtdEstadosVisitados += 1;
        }

        if (estadoAtual == null) {
            System.out.println("Impossível encontrar uma solução.");
            return null;

        } else {
            System.out.println("Solução por busca gulosa encontrada -> " + estadoAtual.jogoEmString());
            System.out.println("Quantidade de jogadas necessárias da raiz até a solução -> " + estadoAtual.getNivel());
            System.out.println("Quantidade de nós visitados -> " + qtdEstadosVisitados);
            System.out.println("");

            return obterCaminho(estadoAtual);
        }
    }

    public ArrayList<EstadoJogo> Aestrela(EstadoJogo estadoInicial) {

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
            
            return obterCaminho(estadoAtual);
        }
    }

    private ArrayList<EstadoJogo> obterCaminho(EstadoJogo estado) {

        Stack<EstadoJogo> pilha = new Stack<>();
        while (estado != null) {
            pilha.push(estado);
            estado = estado.getPai();
        }
        ArrayList<EstadoJogo> caminhoPercorrido = new ArrayList<>();
        while (!pilha.empty()) {
            caminhoPercorrido.add(pilha.pop());
        }
        return caminhoPercorrido;
    }

    public static void main(String[] args) {

        Integer[][] jogoInicial = new Integer[3][3];
        jogoInicial[0][0] = 7;
        jogoInicial[0][1] = 2;
        jogoInicial[0][2] = 4;
        jogoInicial[1][0] = 5;
        jogoInicial[1][1] = 0;
        jogoInicial[1][2] = 6;
        jogoInicial[2][0] = 8;
        jogoInicial[2][1] = 3;
        jogoInicial[2][2] = 1;

        EstadoJogo raiz = new EstadoJogo(jogoInicial);
        //raiz.gerarEstadoInicial();

        System.out.println("Estado inicial -> " + raiz.jogoEmString());

        Busca busca = new Busca();
        busca.buscaGulosa(raiz);
        busca.Aestrela(raiz);
    }
}
