package dominioDoProblema;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

public class DisplayDados extends JPanel implements ActionListener {
    //global
    private TratamentoDado dado1, dado2;
    private final int delay = 10;
    private Timer clock;
    private Regra regras;
    private DisplayMesa display;
    private int pontuacao = 0;
    private int resultado = 2;
    protected Mesa mesa;

    public DisplayDados(DisplayMesa displ, Mesa table) {
        setBackground(new Color(0, 153, 51));
        setBorder(new MatteBorder(
            10,
            10,
            10,
            10,
            (Color) new Color(102, 102, 0)));
        display = displ;
        regras = new Regra();
        dado1 = new TratamentoDado();
        dado2 = new TratamentoDado();
        clock = new Timer(delay, this);
        mesa = table;
    }

    // evento de clicar em (lancar dados e confirmar apostas)
    public void actionPerformed(ActionEvent e) {

        if (diceAreRolling()) {

            if (!clock.isRunning()) clock.restart();

            if (dado1.isRolling()) {
                dado1.avoidCollision(dado2);

            } else if (dado2.isRolling()) {
                dado2.avoidCollision(dado1);
            }
        } else {
            clock.stop();
            int totalSomaDados = dado1.getNumDots() + dado2.getNumDots();
            int dadoA = dado1.getNumDots();
            int dadoB = dado2.getNumDots();
            int point = totalSomaDados;
            int result = regras.regraPrincipal(totalSomaDados);

            display.update(result, point, totalSomaDados, dadoA, dadoB);

        }
        repaint();
    }

    // lancar dados ao click em lancarDadosButton
    public void rollDice() {
        TratamentoDado.setLimites(3, getWidth() - 3, 3, getHeight() - 3);
        dado1.roll();
        dado2.roll();
        clock.start();
    }

    // returns true se dados rolando; 
    public boolean diceAreRolling() {
        return dado1.isRolling() || dado2.isRolling();
    }

    // repaint
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dado1.desenha(g);
        dado2.desenha(g);
    }

    //GET AND SET
    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    //TO STRING
    public String toStringdado1() {
        return "" + dado1;
    }

    public String toStringdado2() {
        return "" + dado2;
    }

    public String toStringResultado() {
        return "" + resultado;
    }
}
