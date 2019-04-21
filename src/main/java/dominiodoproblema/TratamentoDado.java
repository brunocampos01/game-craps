package  dominiodoproblema;

import java.awt.Graphics;
import java.awt.Color;

import org.apache.log4j.Logger;

public class TratamentoDado extends Dado {
    //global
    private static final double DESACELERACAO = 0.97,
        speedFactor = 0.07,
        speedLimit = 1.5;

    private static int tableLeft, tableRight, tableTop, tableBottom;
    private final int dadoTamanho = 50;
    private int xCenter, yCenter;
    private double xSpeed, ySpeed;

    private static Logger logger = Logger.getLogger(TratamentoDado.class);

    // sets the "table" limites
    public static void setLimites(int left, int right, int top, int bottom) {
        tableLeft = left;
        tableRight = right;
        tableTop = top;
        tableBottom = bottom;
    }

    public TratamentoDado() {
        xCenter = -1;
        yCenter = -1;
    }

    public void roll() {
        super.lancarDados();
        int width = tableRight - tableLeft;
        int height = tableBottom - tableTop;
        xCenter = tableLeft;
        yCenter = tableTop + height / 2;
        xSpeed = width * (Math.random() + 1) * speedFactor;
        ySpeed = height * (Math.random() - .5) * 2. * speedFactor;

    }

    public boolean isRolling() {
        return xSpeed > speedLimit || xSpeed < -speedLimit || ySpeed > speedLimit || ySpeed < -speedLimit;
    }

    public void avoidCollision(TratamentoDado outro) {
        if (outro == this) return;

        while (Math.abs(xCenter - outro.xCenter) < dadoTamanho && Math.abs(yCenter - outro.yCenter) < dadoTamanho)
            move();
    }

    private void move() {
        xCenter += xSpeed;
        yCenter += ySpeed;
        int radius = dadoTamanho / 2;

        if (xCenter < tableLeft + radius) {
            xCenter = tableLeft + radius;
            xSpeed = -xSpeed;
        }

        if (xCenter > tableRight - radius) {
            xCenter = tableRight - radius;
            xSpeed = -xSpeed;
        }

        if (yCenter < tableTop + radius) {
            yCenter = tableTop + radius;
            ySpeed = -ySpeed;
        }

        if (yCenter > tableBottom - radius) {
            yCenter = tableBottom - radius;
            ySpeed = -ySpeed;
        }
    }

    public void desenha(Graphics g) {
        if (xCenter < 0 || yCenter < 0) return;

        else if (isRolling()) {
            move();
            drawRolling(g);
            desacelerar();

        } else {
            drawStopped(g);

        }
    }

    public void desacelerar() {
        xSpeed *= DESACELERACAO;
        ySpeed *= DESACELERACAO;
    }

    private void drawRolling(Graphics g) {
        int x = xCenter - dadoTamanho / 2 + (int) (3 * Math.random()) - 1;
        int y = yCenter - dadoTamanho / 2 + (int) (3 * Math.random()) - 1;
        g.setColor(Color.RED);

        if (x % 2 != 0) g.fillRoundRect(x, y, dadoTamanho, dadoTamanho, dadoTamanho / 4, dadoTamanho / 4);
        else g.fillOval(x - 2, y - 2, dadoTamanho + 4, dadoTamanho + 4);

        Dado dado = new Dado();
        dado.lancarDados();
        desenhaPontos(g, x, y, dado.getNumDots());
    }

    private void drawStopped(Graphics g) {
        int x = xCenter - dadoTamanho / 2;
        int y = yCenter - dadoTamanho / 2;
        long tempo = 5;
        g.setColor(Color.RED);
        g.fillRoundRect(x, y, dadoTamanho, dadoTamanho, dadoTamanho / 4, dadoTamanho / 4);
        desenhaPontos(g, x, y, getNumDots());

        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            logger.error("Error: " + TratamentoDado.class.getName());
        }
    }

    //desenha o numero de pontos dos dados
    private void desenhaPontos(Graphics g, int x, int y, int numFace) {
        g.setColor(Color.WHITE);

        int dotSize = dadoTamanho / 4;
        int step = dadoTamanho / 8;
        int x1 = x + step - 1;
        int x2 = x + 3 * step;
        int x3 = x + 5 * step + 1;
        int y1 = y + step - 1;
        int y2 = y + 3 * step;
        int y3 = y + 5 * step + 1;

        switch (numFace) {
            case 1:
                g.fillOval(x2, y2, dotSize, dotSize);
                break;
            case 2:
                g.fillOval(x1, y1, dotSize, dotSize);
                g.fillOval(x3, y3, dotSize, dotSize);
                break;
            case 3:
                g.fillOval(x1, y3, dotSize, dotSize);
                g.fillOval(x2, y2, dotSize, dotSize);
                g.fillOval(x3, y1, dotSize, dotSize);
                break;
            case 4:
                g.fillOval(x1, y1, dotSize, dotSize);
                g.fillOval(x1, y3, dotSize, dotSize);
                g.fillOval(x3, y1, dotSize, dotSize);
                g.fillOval(x3, y3, dotSize, dotSize);
                break;
            case 5:
                g.fillOval(x1, y1, dotSize, dotSize);
                g.fillOval(x1, y3, dotSize, dotSize);
                g.fillOval(x2, y2, dotSize, dotSize);
                g.fillOval(x3, y1, dotSize, dotSize);
                g.fillOval(x3, y3, dotSize, dotSize);
                break;
            case 6:
                g.fillOval(x1, y1, dotSize, dotSize);
                g.fillOval(x1, y3, dotSize, dotSize);
                g.fillOval(x2, y1, dotSize, dotSize);
                g.fillOval(x2, y3, dotSize, dotSize);
                g.fillOval(x3, y1, dotSize, dotSize);
                g.fillOval(x3, y3, dotSize, dotSize);
                break;
            default:
                logger.error("Error: " +numFace);
        }
    }
}

