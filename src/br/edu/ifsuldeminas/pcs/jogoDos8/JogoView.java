package br.edu.ifsuldeminas.pcs.jogoDos8;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Yuri Fernandes
 */
public class JogoView extends JFrame {

    // Cores sobre os botões que representam as casas do tabuleiro.
    private static final Color COR_FUNDO_BOTAO = new Color(66, 134, 244);
    private static final Color COR_FUNDO_BOTAO_PRESSIONADO = Color.LIGHT_GRAY;
    private static final Color COR_NUMERO_BOTAO = /*Color.WHITE*/ Color.BLACK;
    private static final Font FONTE_BOTAO = new Font("Tahoma", Font.PLAIN, 60);
    private static final Font FONTE_BOTAODOIS = new Font("Tahoma", Font.PLAIN, 35);
    private EstadoJogo estadoAtual;
    private EstadoJogo estadoInicial;
    private JButton casas[][]; // Representa as casas no tabuleiro.
    private JButton primeiroBotaoPressionado;
    private final JButton botaoUm, botaoDois, botaoTres;
    private int contadorJogadas;
    private int qtdJogadasIA;
    private boolean flag;
    private ArrayList<EstadoJogo> caminho;
    private int aux = 0;

    /**
     * Permite criar uma janela gráfica que representa o tabuleiro do jogo da
     * velha.
     *
     * @param estadoInicial Matriz de integer 3x3
     */
    public JogoView(EstadoJogo estadoInicial, boolean flag, ArrayList<EstadoJogo> caminho) {
        this.estadoAtual = estadoInicial;
        this.estadoInicial = estadoInicial;
        this.primeiroBotaoPressionado = null;
        this.contadorJogadas = 0;
        this.qtdJogadasIA = 0;
        this.flag = flag;
        this.caminho = caminho;
        
        if (flag) {
            this.botaoUm = new JButton();
            this.botaoDois = new JButton("Desistir");
            this.botaoTres = new JButton();
        } else {
            this.botaoUm = new JButton("Avançar");
            this.botaoDois = new JButton("Voltar");
            this.botaoTres = new JButton("INFO");
        }

        inicializarJanela();
        reposicionarCasas(estadoInicial);
    }

    // Inicializa as configurações iniciais da janela gráfica do jogo.
    private void inicializarJanela() {
        setLayout(new FlowLayout());
        setSize(500, 500);
        setLayout(new GridLayout(4, 3));
        setTitle("8-Puzzle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     *
     * @param estado Estado do jogo
     */
    public final void reposicionarCasas(EstadoJogo estado) {

        // Remove tudo o que já está no tabuleiro
        if (casas != null) {
            for (JButton[] linha : casas) {
                for (JButton casa : linha) {
                    remove(casa);
                }
            }
        }

        casas = new JButton[3][3];

        // Posiciona as novas casas no tabuleiro
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                casas[i][j] = new JButton(estado.getJogo()[i][j] != 0 ? estado.getJogo()[i][j].toString() : "");
                casas[i][j].setBackground(COR_FUNDO_BOTAO);
                casas[i][j].setForeground(COR_NUMERO_BOTAO);
                casas[i][j].setFont(FONTE_BOTAO);
                addEvento(casas[i][j]);

                add(casas[i][j]); // Adiciona casa na janela gráfica.
            }
        }

        estadoAtual = estado;

        if (flag) {
            botaoUm.setEnabled(false);
            botaoTres.setEnabled(false);
            addEvent(botaoDois);
        } else {
            botaoUm.addActionListener((ActionEvent ae) -> {
                if(aux != caminho.size()-1) {
                    aux++;
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            casas[i][j].setText(caminho.get(aux).getJogo()[i][j] != 0 ? caminho.get(aux).getJogo()[i][j].toString() : "");
                        }
                    }
                }
            });
            botaoDois.addActionListener((ActionEvent ae) -> {
                if(aux != 0) {
                    aux--;
                    for(int i = 0; i < 3; i++) {
                        for(int j = 0; j < 3; j++) {
                            casas[i][j].setText(caminho.get(aux).getJogo()[i][j] != 0 ? caminho.get(aux).getJogo()[i][j].toString() : "");
                        }
                    }
                }
            });
            botaoTres.addActionListener((ActionEvent ae) -> {
                System.out.println("tres apertado");
            });
            
        }

        botaoUm.setFont(FONTE_BOTAODOIS);
        botaoDois.setFont(FONTE_BOTAODOIS);
        botaoTres.setFont(FONTE_BOTAODOIS);

        add(botaoUm);
        add(botaoDois);
        add(botaoTres);

        // Atualiza a view
        revalidate();
        repaint();
    }

    private void addEvent(JButton botao) {
        botao.addActionListener((ActionEvent ae) -> {
            setVisible(false);
            JogoView viewIA = new JogoView(estadoInicial, false, caminho);
        });
    }

    private void addEvento(JButton casa) {

        casa.addActionListener((ActionEvent ae) -> {

            if (primeiroBotaoPressionado == null) {
                primeiroBotaoPressionado = casa;
                primeiroBotaoPressionado.setBackground(COR_FUNDO_BOTAO_PRESSIONADO);
                //System.out.println("Primeiro botão pressionado: " + casa.getText());

            } else if (!primeiroBotaoPressionado.getText().equals(casa.getText())) {

                // Procurando a posição das casas na matriz
                int i1 = -1, j1 = -1, i2 = -1, j2 = -1;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (casas[i][j].getText().equals(primeiroBotaoPressionado.getText())) {
                            i1 = i;
                            j1 = j;
                        } else if (casas[i][j].getText().equals(casa.getText())) {
                            i2 = i;
                            j2 = j;
                        }
                        if (i1 != -1 && i2 != -1) {
                            i = 3;
                            j = 3;
                        }
                    }
                }

                ArrayList<EstadoJogo> filhos = estadoAtual.gerarFilhos();

                Integer[][] estadoPretendido = new Integer[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (i == i1 && j == j1) {
                            estadoPretendido[i][j] = casas[i2][j2].getText().equals("") ? 0 : Integer.valueOf(casas[i2][j2].getText());
                        } else if (i == i2 && j == j2) {
                            estadoPretendido[i][j] = casas[i1][j1].getText().equals("") ? 0 : Integer.valueOf(casas[i1][j1].getText());
                        } else {
                            estadoPretendido[i][j] = casas[i][j].getText().equals("") ? 0 : Integer.valueOf(casas[i][j].getText());
                        }
                    }
                }

                for (EstadoJogo filho : filhos) {
                    boolean ehFilho = true;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (!Objects.equals(filho.getJogo()[i][j], estadoPretendido[i][j])) {
                                ehFilho = false;
                                i = 3;
                                j = 3;
                            }
                        }
                    }
                    if (ehFilho) {
                        EstadoJogo novoEstado = new EstadoJogo(estadoPretendido, estadoAtual);
                        estadoAtual = novoEstado;
                        reposicionarCasas(estadoAtual);

                        contadorJogadas++;

                        // Verifica se atingiu um estado final
                        if (estadoAtual.ehEstadoFinal()) {
                            JOptionPane.showMessageDialog(this, "Fim de jogo! Total de jogadas: " + contadorJogadas);
                            setVisible(false);
                            JogoView viewIA = new JogoView(estadoInicial, false, caminho);
                        }
                        break;
                    }
                }

                primeiroBotaoPressionado.setBackground(COR_FUNDO_BOTAO);
                primeiroBotaoPressionado = null;
                estadoAtual.resetarEstadosJaGerados();

                System.out.println("Primeiro botão pressionado: " + i1 + "," + j1);
                System.out.println("Segundo botão pressionado: " + i2 + "," + j2);
            } else {
                // Clicou no mesmo botão duas vezes
                primeiroBotaoPressionado.setBackground(COR_FUNDO_BOTAO);
                primeiroBotaoPressionado = null;
            }
        });
    }

    public int getQtdJogadasIA() {
        return qtdJogadasIA;
    }

    public void setQtdJogadasIA(int qtdJogadasIA) {
        this.qtdJogadasIA = qtdJogadasIA;
    }
}
