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
            System.out.println("Nós Fechados -> " + (qtdEstadosVisitados - filaDeEstados.size()));
            System.out.println("Nós Abertos -> " + filaDeEstados.size());
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
                filho.setValorTotal(estadoAtual.getNivel() + filho.calcularHeuristicaManhattan());
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
            System.out.println("Nós Abertos -> " + (qtdEstadosVisitados - filaDeEstados.size()));
            System.out.println("Nós Fechados -> " + filaDeEstados.size());

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
}
