package br.edu.ifsuldeminas.pcs.jogoDos15.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Yuri Fernandes
 */
public class JogoView extends JFrame {

    // Cores sobre os botões que representam as casas do tabuleiro.
    private static final Color COR_FUNDO_BOTAO = new Color(66, 134, 244);
    private static final Color COR_NUMERO_BOTAO = Color.WHITE;
    private static final Font FONTE_BOTAO = new Font("Tahoma", Font.PLAIN, 60);

    // Representa as casas no tabuleiro.
    private JButton casas[][];

    /**
     * Permite criar uma janela gráfica que representa o tabuleiro do jogo da
     * velha.
     *
     * @param estadoInicial Matriz de integer 3x3
     */
    public JogoView(Integer[][] estadoInicial) {
        inicializarJanela();
        reposicionarCasas(estadoInicial);
    }

    public static void main(String[] args) {

        Integer[][] teste = new Integer[3][3];
        teste[0][0] = 1;
        teste[0][1] = 2;
        teste[0][2] = 3;
        teste[1][0] = 4;
        teste[1][1] = 5;
        teste[1][2] = 6;
        teste[2][0] = 7;
        teste[2][1] = 8;
        teste[2][2] = null;

        JogoView jogoView = new JogoView(teste);
    }

    // Inicializa as configurações iniciais da janela gráfica do jogo.
    private void inicializarJanela() {
        setLayout(new GridLayout(3, 3));
        setSize(500, 500);
        setTitle("Jogo dos 15 (só que com 8)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     *
     * @param estado Matriz 3x3 de Integer
     */
    public final void reposicionarCasas(Integer[][] estado) {

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
                casas[i][j] = new JButton(estado[i][j] != null ? estado[i][j].toString() : "");
                casas[i][j].setBackground(COR_FUNDO_BOTAO);
                casas[i][j].setForeground(COR_NUMERO_BOTAO);
                casas[i][j].setFont(FONTE_BOTAO);
                add(casas[i][j]); // Adiciona casa na janela gráfica.
            }
        }

        // Atualiza a view
        revalidate();
        repaint();
    }
}
