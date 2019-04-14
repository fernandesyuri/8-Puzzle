package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Stack;

public class EstadoJogo implements Comparable<EstadoJogo> {

    public EstadoJogo pai;
    private Integer jogo[][];
    private Integer valorHeuristica;
    private Integer nivel;
    private HashSet<String> estadosJaGerados;
    private ArrayList<EstadoJogo> filhos;

    public EstadoJogo() {
        this.pai = null;
        this.jogo = new Integer[3][3];
        this.valorHeuristica = null;
        this.nivel = 0;
        this.estadosJaGerados = new HashSet<>();
        this.filhos = new ArrayList<>();
    }

    public void addFilho(EstadoJogo clonado) {
        clonado.pai = this;
        this.filhos.add(clonado);
        //clonado.imprimeEstado();
        //clonado.pai.imprimeEstado();
    }

    public EstadoJogo(Integer[][] jogo, EstadoJogo pai) {
        this.pai = pai;
        this.jogo = jogo;
        this.valorHeuristica = null;
        this.nivel = pai.getNivel() + 1;
        this.estadosJaGerados = new HashSet<>();
    }

    public EstadoJogo clonar() {
        EstadoJogo clone = new EstadoJogo();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clone.jogo[i][j] = this.jogo[i][j];
            }
        }
        return clone;
    }

    static int getQuantidadeInversao(Integer[][] arr) {
        int inv_count = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)  {
                for (int k = i; k < 3; k++) {
                    for (int l = j; l < 3; l++) {
                        //System.out.println("posicao "+i+","+j+" comparando com "+k+","+l);
                        if (arr[i][j] > 0 && arr[k][l] > 0 && arr[i][j] > arr[k][l]) {
                            inv_count++;
                        }
                    }
                }
                
            }
        }

        //System.out.println("quantidade inv " + inv_count);

        return inv_count;
    }

    // Retorna true se o quebra cabeca tiver solucao
    private boolean temSolucao(Integer[][] puzzle) {
        // conta quantas inversoes tem no quebra cabeca
        int invCount = getQuantidadeInversao(puzzle);

        // retorna true se a quantidad de inversao for par, ou seja, é possível resolver
        return (invCount % 2 == 0);
    }

    private void gerarEstadoInicial() {
        HashSet<Integer> numeros = new HashSet<>();
        Random gerador = new Random();

        do {
            numeros.clear();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    while (true) {
                        int numeroGerado = gerador.nextInt(9);
                        this.jogo[i][j] = (numeroGerado == 0) ? 0 : numeroGerado;
                        if (numeros.add(this.jogo[i][j])) {
                            break;
                        }
                    }
                }
            }
        } while (!temSolucao(jogo));

        this.estadosJaGerados.add(this.jogoEmString());
    }

    public void calcularHeuristicaManhattan() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (0 != this.jogo[i][j]) {
                    switch (this.jogo[i][j]) {
                        case 1:
                            count += Math.abs(i - 0) + Math.abs(j - 1);
                            break;
                        case 2:
                            count += Math.abs(i - 0) + Math.abs(j - 2);
                            break;
                        case 3:
                            count += Math.abs(i - 1) + Math.abs(j - 0);
                            break;
                        case 4:
                            count += Math.abs(i - 1) + Math.abs(j - 1);
                            break;
                        case 5:
                            count += Math.abs(i - 1) + Math.abs(j - 2);
                            break;
                        case 6:
                            count += Math.abs(i - 2) + Math.abs(j - 0);
                            break;
                        case 7:
                            count += Math.abs(i - 2) + Math.abs(j - 1);
                            break;
                        case 8:
                            count += Math.abs(i - 2) + Math.abs(j - 2);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        this.valorHeuristica = count;
    }

    public String jogoEmString() {
        String jogoString = new String();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //String aux = (this.jogo[i][j] == null) ? "0" : this.jogo[i][j].toString();
                String aux = this.jogo[i][j].toString();
                jogoString = jogoString.concat(aux);
            }
        }
        return jogoString;
    }

    public ArrayList<EstadoJogo> gerarFilhos() {
        int i0 = -1, j0 = -1;
        EstadoJogo clone = this.clonar();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clone.jogo[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    break;
                }
            }
            if (i0 != -1) {
                break;
            }
        }
        //troca 0 com o valor de cima
        if (i0 - 1 >= 0) {
            int aux = clone.jogo[i0 - 1][j0];
            clone.jogo[i0 - 1][j0] = clone.jogo[i0][j0];
            clone.jogo[i0][j0] = aux;
            String jogoString = clone.jogoEmString();
            if (this.estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                clone.nivel = this.nivel + 1;
                addFilho(clone);
            }
        }
        //troca 0 com o valor de baixo
        if (i0 + 1 < 3) {
            clone = this.clonar();
            int aux = clone.jogo[i0 + 1][j0];
            clone.jogo[i0 + 1][j0] = clone.jogo[i0][j0];
            clone.jogo[i0][j0] = aux;
            String jogoString = clone.jogoEmString();
            if (this.estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                clone.nivel = this.nivel + 1;
                addFilho(clone);
            }
        }
        //troca 0 com o valor a direita
        if (j0 + 1 < 3) {
            clone = this.clonar();
            int aux = clone.jogo[i0][j0 + 1];
            clone.jogo[i0][j0 + 1] = clone.jogo[i0][j0];
            clone.jogo[i0][j0] = aux;
            String jogoString = clone.jogoEmString();
            if (this.estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                clone.nivel = this.nivel + 1;
                addFilho(clone);
            }
        }
        //troca 0 com o valor a esquerda
        if (j0 - 1 >= 0) {
            clone = this.clonar();
            int aux = clone.jogo[i0][j0 - 1];
            clone.jogo[i0][j0 - 1] = clone.jogo[i0][j0];
            clone.jogo[i0][j0] = aux;
            String jogoString = clone.jogoEmString();
            if (this.estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                clone.nivel = this.nivel + 1;
                addFilho(clone);
            }
        }
        //System.out.println("to no nivel "+this.nivel);
        return this.filhos;
    }

    public void resetarEstadosJaGerados() {
        estadosJaGerados = new HashSet<>();
    }

    public boolean ehEstadoFinal() {

        // 1 2 3 4 5 6 7 8 0
        if (Objects.equals(jogo[0][0], 0) && Objects.equals(jogo[0][1], 1) && Objects.equals(jogo[0][2], 2)
                && Objects.equals(jogo[1][0], 3) && Objects.equals(jogo[1][1], 4) && Objects.equals(jogo[1][2], 5)
                && Objects.equals(jogo[2][0], 6) && Objects.equals(jogo[2][1], 7) && Objects.equals(jogo[2][2], 8)) {
            return true;
        }

        // 0 1 2 3 4 5 6 7 8
        if (Objects.equals(jogo[0][0], 1) && Objects.equals(jogo[0][1], 2) && Objects.equals(jogo[0][2], 3)
                && Objects.equals(jogo[1][0], 4) && Objects.equals(jogo[1][1], 5) && Objects.equals(jogo[1][2], 6)
                && Objects.equals(jogo[2][0], 7) && Objects.equals(jogo[2][1], 8) && Objects.equals(jogo[2][2], 0)) {
            return true;
        }

        return false;
    }

    public Integer[][] getJogo() {
        return jogo;
    }

    public void setJogo(Integer jogo[][]) {
        this.jogo = jogo;
    }

    public Integer getValorHeuristica() {
        return this.valorHeuristica;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void imprimeEstado() {
        for (int i = 0; i < jogoEmString().length(); i++) {
            if (i == 3 || i == 6) {
                System.out.print("\n" + jogoEmString().charAt(i));
            } else {
                System.out.print(jogoEmString().charAt(i));
            }
        }
        System.out.println("\n");
    }

    @Override
    public int compareTo(EstadoJogo t) {
        if (t.getValorHeuristica() < getValorHeuristica()) {
            return 1;
        }
        if (t.getValorHeuristica() > getValorHeuristica()) {
            return -1;
        }
        if (t.getValorHeuristica() == getValorHeuristica()) {
            if (t.getNivel() < getNivel()) {
                return -1;
            }
            if (t.getNivel() > getNivel()) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String args[]) {
        EstadoJogo e = new EstadoJogo();
        
        //e.gerarEstadoInicial();
        //System.out.println("estado gerado ");
        //e.imprimeEstado();
        
        e.jogo[0][0] = 4;
        e.jogo[0][1] = 3;
        e.jogo[0][2] = 1;
        e.jogo[1][0] = 7;
        e.jogo[1][1] = 6;
        e.jogo[1][2] = 0;
        e.jogo[2][0] = 8;
        e.jogo[2][1] = 2;
        e.jogo[2][2] = 5;
        
        if(e.temSolucao(e.getJogo())) {
            System.out.println("tem solucao");
        }
        
        JogoView view = new JogoView(e);

        EstadoJogo objetivo = new EstadoJogo();
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                objetivo.jogo[i][j] = count++;
            }
        }
        //e.imprimeEstado();

        Busca busca = new Busca();
        EstadoJogo solucao = busca.buscaGulosa(e, objetivo);

        Stack<EstadoJogo> pilha = new Stack<>();
        do {
            pilha.push(solucao);
            solucao = solucao.pai;
        } while (solucao != null);

        System.out.println("Caminho para chegar no resultado:");

        while (!(pilha.isEmpty())) {
            System.out.println("Estado: ");
            pilha.pop().imprimeEstado();
        }
        System.out.println("Encontrei o estado final");
    }
}
