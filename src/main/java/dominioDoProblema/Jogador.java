package dominioDoProblema;

public class Jogador {
    //global
    protected String nome;
    protected int saldo = 100;
    protected boolean ehShooter;
    protected boolean daVez = true;
    protected boolean vencedor;
    protected DisplayMesa display;

    public Jogador(DisplayMesa displ) {
        display = displ;
    }

    public Jogador() {
    }

    public boolean informarDaVez() {
        return daVez;
    }

    public String informarNome() {
        return nome;
    }

    public boolean informarVencedor() {
        return vencedor;
    }

    public boolean informarShooter() {
        return ehShooter;
    }

    public void iniciar() {
        vencedor = false;
    }

    public void assumirNome(String idJogador) {
        nome = idJogador;
    }

    public void habilitar() {
        daVez = true;
    }

    public void desabilitar() {
        daVez = false;
    }

    public void assumirVencedor() {
        vencedor = true;
    }

    public boolean ehShooter() {
        return ehShooter = true;
    }

    public boolean NaoEhShooter() {
        return ehShooter = false;
    }

    protected boolean simbolo;

    public boolean informarSimbolo() {
        return simbolo;
    }
}
