package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

public class Busca {

    ArrayList<EstadoJogo> nosParaExpansao;
    ArrayList<EstadoJogo> nosJaExpandidos;

    public ArrayList<EstadoJogo> buscaGulosa(EstadoJogo estadoInicial) {

        HashSet<String> estadosJaVisitados = new HashSet<>(); // HashSet para impedir a criação de filhos iguais
        int qtdEstadosVisitados = 0;

        PriorityQueue<EstadoJogo> filaDeEstados = new PriorityQueue<>(); // Fila de estados a serem expandidos, por ordem de prioridade

        EstadoJogo estadoAtual = estadoInicial;

        while (estadoAtual != null && !estadoAtual.ehEstadoFinal()) { // Faz enquanto estado nao for nulo ou solução

            estadosJaVisitados.add(estadoAtual.jogoEmString()); // Adiciona a lista de já visitados o estado atual e
            estadoAtual.gerarFilhos(estadosJaVisitados);        // gera seus filhos (expande)

            for (EstadoJogo filho : estadoAtual.getFilhos()) {  // Pega os filhos criados e adiciona a fila de estados
                filaDeEstados.add(filho);
            }

            estadoAtual = filaDeEstados.poll();                 // Pega o primeiro filho da fila (menor custo) e define como estado atual
            qtdEstadosVisitados += 1;                           // Incrementa estados visitados e volta pra condiçao while
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
                filho.setValorTotal(estadoAtual.getNivel() + filho.calcularHeuristicaManhattan()); // f(n) = g(n) + h(n);
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
            System.out.println("");

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
