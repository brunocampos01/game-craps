package DominioDoProblema;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.awt.List;

public class Lance implements Jogada {
    //global
    protected int dado1 = 0;
    protected int dado2 = 0;
    protected int somaDados = 0;
    protected int vitoriasJogador1 = 0;
    protected int vitoriasJogador2 = 0;
    protected int derrotasJogador1 = 0;
    protected int derrotasJogador2 = 0;
    protected int totalApostasJogador1 = 0;
    protected int totalApostasJogador2 = 0;
    protected int point = 0;
    protected List apostasJogador1;
    protected List apostasJogador2;
    protected int contaJogada;//sincronismo entre jogadores
    protected int resultadoRodada;

    public int informarDado1() {
        return dado1;
    }

    public int informarDado2() {
        return dado2;
    }

    public int informarSomaDados() {
        return somaDados;
    }

    public int informarVitoriasJogador1() {
        return vitoriasJogador1;
    }

    public int informarvitoriasJogador2() {
        return vitoriasJogador2;
    }

    public int informarDerrotasJogador1() {
        return derrotasJogador1;
    }

    public int informarDerrotasJogador2() {
        return derrotasJogador2;
    }

    public int informarTotalApostasJogador1() {
        return totalApostasJogador1;
    }

    public int informarTotalApostasJogador2() {
        return totalApostasJogador2;
    }

    public int informarPoint() {
        return point;
    }

    public List informarApostasJogador1() {
        return apostasJogador1;
    }

    public List informarApostasJogador2() {
        return apostasJogador2;
    }

    public int contaJogada() {
        return contaJogada;
    }

    public int resultadoRodada() {
        return resultadoRodada;
    }

    public void assumir(
        int valdado1,
        int valdado2,
        int somaDeDados,
        int vitoriasJogador1,
        int vitoriasJogador2,
        int derrotasJogador1,
        int derrotasJogador2,
        int totalApostasJogador1,
        int totalApostasJogador2,
        int point,
        List apostasJogador1,
        List apostasJogador2,
        int contaJogada,
        int resultadoRodada) {
        dado1 = valdado1;
        dado2 = valdado2;
        somaDados = somaDeDados;
        this.vitoriasJogador1 = vitoriasJogador1;
        this.vitoriasJogador2 = vitoriasJogador2;
        this.derrotasJogador1 = derrotasJogador1;
        this.derrotasJogador2 = derrotasJogador2;
        this.totalApostasJogador1 = totalApostasJogador1;
        this.totalApostasJogador2 = totalApostasJogador2;
        this.point = point;
        this.apostasJogador1 = apostasJogador1;
        this.apostasJogador2 = apostasJogador2;
        this.contaJogada = contaJogada;
        this.resultadoRodada = resultadoRodada;
    }

}
