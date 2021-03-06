package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class EstadoJogo implements Comparable<EstadoJogo> {

    public EstadoJogo pai;
    private Integer jogo[][];
    private Integer valorHeuristica;
    private Integer valorTotal;
    private Integer nivel;
    private HashSet<String> estadosJaGerados;
    private ArrayList<EstadoJogo> filhos;

    // Construtor sem parâmetros
    public EstadoJogo() {
        this.pai = null;
        this.jogo = new Integer[3][3];
        this.valorHeuristica = null;
        this.valorTotal = null;
        this.nivel = 0;
        this.estadosJaGerados = new HashSet<>();
        this.filhos = new ArrayList<>();
    }

    // Construtor passando o jogo inicial (utilizado na entrada de dados manual)
    public EstadoJogo(Integer[][] jogo) {
        this.pai = null;
        this.jogo = jogo;
        this.valorHeuristica = null;
        this.valorTotal = null;
        this.nivel = 0;
        this.estadosJaGerados = new HashSet<>();
        this.filhos = new ArrayList<>();
    }

    // Construtor passando o jogo inicial e o pai (utilizado na jogada manual)
    public EstadoJogo(Integer[][] jogo, EstadoJogo pai) {
        this.pai = pai;
        this.jogo = jogo;
        this.valorHeuristica = null;
        this.valorTotal = null;
        this.nivel = pai.getNivel() + 1;
        this.estadosJaGerados = new HashSet<>();
        this.filhos = new ArrayList<>();
    }

    public void addFilho(EstadoJogo clonado) {
        clonado.pai = this;
        this.filhos.add(clonado);
    }

    public EstadoJogo clonar() {
        EstadoJogo clone = new EstadoJogo();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                clone.jogo[i][j] = this.jogo[i][j];
        return clone;
    }

    // Conta o número de inversões para verificar se o estado inicial tem solução
    static int getQuantidadeInversao(Integer[][] arr) {
        int inv_count = 0, cont = 0;
        Integer puzzle[] = new Integer[9];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                puzzle[cont++] = arr[i][j];

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = i + 1; j < puzzle.length; j++)
                if ((puzzle[i] > puzzle[j]) && (puzzle[i] > 0) && (puzzle[j] > 0))
                    inv_count++;
        }
        return inv_count;
    }

    // Retorna true se o quebra cabeca tiver solucao
    public boolean temSolucao(Integer[][] puzzle) {
        // conta quantas inversoes tem no quebra cabeca
        int invCount = getQuantidadeInversao(puzzle);
        // retorna true se a quantidade de inversao for par, ou seja, é possível resolver
        return (invCount % 2 == 0);
    }

    // Gera um estado inicial aleatório e verifica se tem solução
    public void gerarEstadoInicial() {
        HashSet<Integer> numeros = new HashSet<>();
        Random gerador = new Random();

        do {
            numeros.clear();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    while (true) {
                        int numeroGerado = gerador.nextInt(9);
                        this.jogo[i][j] = (numeroGerado == 0) ? 0 : numeroGerado;
                        if (numeros.add(this.jogo[i][j]))
                            break;
                    }
                }
            }
        } while (!temSolucao(jogo));

        this.estadosJaGerados.add(this.jogoEmString());
    }

    public int calcularHeuristicaManhattan() {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (this.jogo[i][j]) {
                    case 0:
                        count += Math.abs(i - 0) + Math.abs(j - 0);
                        break;
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
        this.valorHeuristica = count;
        return count;
    }

    // Converte a matriz do estado do jogo para uma string de linha única
    public String jogoEmString() {
        String jogoString = new String();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String aux = this.jogo[i][j].toString();
                jogoString = jogoString.concat(aux);
            }
        }
        return jogoString;
    }

    // Gera todos os filhos possíveis, sem repetição
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
        return this.filhos;
    }

    public ArrayList<EstadoJogo> gerarFilhos(HashSet<String> estadosJaGerados) {
        this.estadosJaGerados = estadosJaGerados;
        return gerarFilhos();
    }

    // Utilizado na jogada manual
    public void resetarEstadosJaGerados() {
        estadosJaGerados = new HashSet<>();
    }

    // Verifica se o estado final é um estado final
    public boolean ehEstadoFinal() {
        if (Objects.equals(jogo[0][0], 0) && Objects.equals(jogo[0][1], 1) && Objects.equals(jogo[0][2], 2)
                && Objects.equals(jogo[1][0], 3) && Objects.equals(jogo[1][1], 4) && Objects.equals(jogo[1][2], 5)
                && Objects.equals(jogo[2][0], 6) && Objects.equals(jogo[2][1], 7) && Objects.equals(jogo[2][2], 8)) {
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

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ArrayList<EstadoJogo> getFilhos() {
        return filhos;
    }

    public EstadoJogo getPai() {
        return pai;
    }

    public void imprimeEstado() {
        for (int i = 0; i < jogoEmString().length(); i++) {
            if (i == 3 || i == 6)
                System.out.print("\n" + jogoEmString().charAt(i));
            else
                System.out.print(jogoEmString().charAt(i));
        }
        System.out.println("\n");
    }

    @Override
    public int compareTo(EstadoJogo t) {
        if (valorTotal == null) {
            if (t.getValorHeuristica() < getValorHeuristica())
                return 1;
            if (t.getValorHeuristica() > getValorHeuristica())
                return -1;
            if (Objects.equals(t.getValorHeuristica(), getValorHeuristica())) {
                if (t.getNivel() < getNivel())
                    return 1;
                if (t.getNivel() > getNivel())
                    return -1;
            }
            return 0;
        } else {
            if (t.getValorTotal() < valorTotal)
                return 1;
            if (t.getValorTotal() > valorTotal)
                return -1;
            if (Objects.equals(t.getValorTotal(), valorTotal)) {
                if (t.getNivel() < nivel)
                    return 1;
                if (t.getNivel() > nivel)
                    return -1;
            }
            return 0;
        }
    }
}
