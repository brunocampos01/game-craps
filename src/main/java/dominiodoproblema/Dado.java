package  dominiodoproblema;

public class Dado {
    private int face;

    public void lancarDados() {
        face = (int) (Math.random() * 6 + 1);
    }

    public int lancarDado() {
        return face = (int) (Math.random() * 6 + 1);
    }

    public int getNumDots() {
        return face;
    }
}
