package DominioDoProblema;

import javax.swing.JFrame;

public class CrapsTest {
    public static void main(String[] args) {
        DisplayMesa janela;
        janela = new DisplayMesa();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
    }
}
