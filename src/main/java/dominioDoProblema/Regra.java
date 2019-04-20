package dominioDoProblema;

public class Regra {
    //global
    private int point = 0;

    /**
     * 1 if player won
     * -1 if player lost
     * 0 if player continues rolling
     */
    public int regraPrincipal(int total) {
        int result = 0;
        System.out.println("getPoint " + getPoint());
        System.out.println("total " + total);
        if (point == 0) {//se primeira rodada (POINT)
            if (total == 2 || total == 3 || total == 12) {   //perde na primeira rodada
                result = -1;
            } else if (total == 7 || total == 11) {    //ganha na primeira rodada
                result = 1;
            } else {  // deu POINT na primeira rodada
                point = total;
            }
        } else {// depois da primeira rodada (win or lost)
            if (total == point) {    //win
                point = 0;
                result = 1;
            } else if (total == 2 || total == 3 || total == 12) {    //lost
                point = 0;
                result = -1;
            } else {
                point = total;
            }
        }
        return result;
    }

    public int getPoint() {
        return point;
    }

    public String toStringPoint() {
        return " " + point;
    }
}
