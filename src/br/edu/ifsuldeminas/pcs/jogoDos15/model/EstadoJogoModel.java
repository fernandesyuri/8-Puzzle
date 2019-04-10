package br.edu.ifsuldeminas.pcs.jogoDos15.model;

import br.edu.ifsuldeminas.pcs.jogoDos15.view.JogoView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class EstadoJogoModel implements Comparable<EstadoJogoModel>{

    private EstadoJogoModel pai;
    private Integer jogo[][];
    private Integer valorHeuristica;

    public EstadoJogoModel() {
        this.pai = null;
        this.jogo = new Integer[3][3];
        this.valorHeuristica = null;
    }

    public EstadoJogoModel clonar() {
        EstadoJogoModel clone = new EstadoJogoModel();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                clone.jogo[i][j] = this.jogo[i][j];
            }
        }
        return clone;
    }

    public void gerarEstadoInicial() {
        HashSet<Integer> numeros = new HashSet<>();
        Random gerador = new Random();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                while (true) {
                    int numeroGerado = gerador.nextInt(9);
                    this.jogo[i][j] = (numeroGerado == 0) ? null : numeroGerado;
                    if (numeros.add(this.jogo[i][j])) {
                        break;
                    }
                }
            }
        }
    }
    
    public void calcularHeuristicaManhattan(){
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(null!=this.jogo[i][j])switch (this.jogo[i][j]) {
                    case 1:
                        count+=Math.abs(i-0)+Math.abs(j-1);
                        break;
                    case 2:
                        count+=Math.abs(i-0)+Math.abs(j-2);
                        break;
                    case 3:
                        count+=Math.abs(i-1)+Math.abs(j-0);
                        break;
                    case 4:
                        count+=Math.abs(i-1)+Math.abs(j-1);
                        break;
                    case 5:
                        count+=Math.abs(i-1)+Math.abs(j-2);
                        break;
                    case 6:
                        count+=Math.abs(i-2)+Math.abs(j-0);
                        break;
                    case 7:
                        count+=Math.abs(i-2)+Math.abs(j-1);
                        break;
                    case 8:
                        count+=Math.abs(i-2)+Math.abs(j-2);
                        break;
                    default:
                        break;
                }
            }
        }
        this.valorHeuristica=count;
    }

    public String jogoEmString() {
        String jogoString = new String();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String aux = (this.jogo[i][j] == null) ? "0" : this.jogo[i][j].toString();
                jogoString = jogoString.concat(aux);
            }
        }
        return jogoString;
    }

    public ArrayList<EstadoJogoModel> gerarFilhos(HashSet<String> estadosJaGerados) {
        ArrayList<EstadoJogoModel> filhos = new ArrayList<EstadoJogoModel>();
        int i0 = -1, j0 = -1;
        EstadoJogoModel clone = this.clonar();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (clone.jogo[i][j] == null) {
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
            if (estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                filhos.add(clone);
            }
        }
        //troca 0 com o valor de baixo
        if (i0 + 1 < 3) {
            clone = this.clonar();
            int aux = clone.jogo[i0 + 1][j0];
            clone.jogo[i0 + 1][j0] = clone.jogo[i0][j0];
            clone.jogo[i0][j0] = aux;
            String jogoString = clone.jogoEmString();
            if (estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                filhos.add(clone);
            }
        }
        //troca 0 com o valor a direita
        if (j0 + 1 < 3) {
            clone = this.clonar();
            int aux = clone.jogo[i0][j0 + 1];
            clone.jogo[i0][j0 + 1] = clone.jogo[i0][j0];
            clone.jogo[i0][j0] = aux;
            String jogoString = clone.jogoEmString();
            if (estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                filhos.add(clone);
            }
        }
        //troca 0 com o valor a esquerda
        if (j0 - 1 >= 0) {
            clone = this.clonar();
            int aux = clone.jogo[i0][j0 - 1];
            clone.jogo[i0][j0 - 1] = clone.jogo[i0][j0];
            clone.jogo[i0][j0] = aux;
            String jogoString = clone.jogoEmString();
            if (estadosJaGerados.add(jogoString)) {
                clone.pai = this;
                clone.calcularHeuristicaManhattan();
                filhos.add(clone);
            }
        }
        return filhos;
    }

    public Integer[][] getJogo() {
        return jogo;
    }

    public void setJogo(Integer jogo[][]) {
        this.jogo = jogo;
    }

    public static void main(String args[]) {
        HashSet<String> todosOsEstadosJaGerados = new HashSet<String>();
        EstadoJogoModel e = new EstadoJogoModel();
        e.gerarEstadoInicial();

        JogoView view2 = new JogoView(e.getJogo());

        System.out.println("Estado inicial: " + e.jogoEmString());
        ArrayList<EstadoJogoModel> teste = e.gerarFilhos(todosOsEstadosJaGerados);
        for(EstadoJogoModel e1 : teste){
            System.out.println(e1.jogoEmString());
            System.out.println(e1.valorHeuristica);
        }

        for (EstadoJogoModel e1 : teste) {
            JogoView view = new JogoView(e1.getJogo());
        }
    }

    @Override
    public int compareTo(EstadoJogoModel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
