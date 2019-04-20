package dominioDoProblema;

import java.awt.List;

import rede.AtorNetGames;

public class AtorJogador {
    //global
    protected Mesa mesa;
    protected rede.AtorNetGames rede;
    protected DisplayMesa display;
    protected DisplayDados painelD;
    protected String idUsuario;
    protected String adversario;

    public AtorJogador(DisplayMesa displ, Mesa table, dominioDoProblema.DisplayDados painel) {
        super();
        rede = new AtorNetGames(this);
        display = displ;
        mesa = table;
        painelD = painel;
    }

    public DisplayMesa informarJanela() {
        return display;
    }

    public String obterDadosConexao() {
        idUsuario = display.obterIdJogador();
        String servidor = display.obterIdServidor();
        return servidor;
    }

    public int conectar() {
        boolean conectado = mesa.informarConectado();

        if (!conectado) {
            String dadosConexao = this.obterDadosConexao();
            boolean exito = rede.conectar(dadosConexao, idUsuario);

            if (exito) {
                mesa.estabelecerConectado(true);
                return 0;
            } else {
                return 2;   //2 - Tentativa de conexao falhou
            }
        } else {
            return 1;      //1 - tentativa de conexão com conexão previamente estabelecida
        }
    }

    public int desconectar() {
        boolean conectado = mesa.informarConectado();

        if (conectado) {
            boolean exito = rede.desconectar();

            if (exito) {
                mesa.estabelecerConectado(false);
                return 3;   //3 - desconexao com exito
            } else {
                return 5;
            }
        } else {
            return 4;
        }
    }

    public int iniciarPartida() {
        boolean conectado = false;
        int resultadoInicio = 7;
        boolean emAndamento = mesa.informarEmAndamento();

        if (!emAndamento) {
            conectado = mesa.informarConectado();

            if ((!emAndamento && conectado)) {
                rede.iniciarPartida();
                return resultadoInicio = 6;
            }
        }
        return resultadoInicio;
    }

    public boolean avaliarInterrupcao() {
        return true;
    }

    public int notificarExitoIniciacao() {
        return 6;
    }

    public int notificarFalhainiciacao() {
        return 7;
    }

    public void tratarIniciarPartida(Integer posicao) {
        mesa.esvaziar();
        mesa.criarJogador(idUsuario);
        String idJogador = rede.informarNomeAdversario(idUsuario);
        adversario = idJogador;
        mesa.criarJogador(idJogador);
        mesa.habilitar(posicao);
        exibirEstadoInicial();
    }

    public void exibirEstadoInicial() {
        display.setTitle(idUsuario);
        display.jogador1Label.setText("" + idUsuario);
        display.jogador2Label.setText("" + adversario);
        display.apostasJogador2Label.setText("Apostas de " + idUsuario);
        display.apostasJogador1Label.setText("Apostas de " + adversario);
        display.atualizacaoStatusPartida(3);
    }

    public void enviarJogada(
                            int dado1,
                            int dado2,
                            int somaDados,
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

                            dominioDoProblema.Lance lance = mesa.informarJogada(
                                                            dado1,
                                                            dado2,
                                                            somaDados,
                                                            mesa.vitoriasJogador1,
                                                            mesa.vitoriasJogador2,
                                                            mesa.derrotasJogador1,
                                                            mesa.derrotasJogador2,
                                                            totalApostasJogador1,
                                                            totalApostasJogador2,
                                                            point,
                                                            apostasJogador1,
                                                            apostasJogador2,
                                                            mesa.contaJogada,
                                                            mesa.resultadoRodada);

        mesa.conferirTodosResultados();
        rede.enviarJogada(lance);
    }

    public void receberJogada(Lance jogada) {
        mesa.receberJogada(
                        jogada.informarDado1(),
                        jogada.informarDado2(),
                        jogada.informarSomaDados(),
                        jogada.informarTotalApostasJogador1(),
                        jogada.informarTotalApostasJogador2(),
                        jogada.informarPoint(),
                        jogada.informarApostasJogador1(),
                        jogada.informarApostasJogador2(),
                        jogada.contaJogada(),
                        jogada.resultadoRodada());

        // verifica rodada e verifica jogada
        // somente executa quando lancouDados == true(o adversario lançou os dados)
        mesa.conferirTodosResultados();
        mesa.habilitarShooter();
        mesa.habilitarBotoes();

        display.notificarResultado(15);

        if (mesa.contaJogada > 1 && mesa.point != 0) {
            display.atualizacaoStatusPartida(1);
        }

        if (mesa.resultadoRodada == 0) {//point
            mesa.atualizarApostasDoAdversario();
            display.pointText.setText("" + mesa.toStringPoint());
        }
    }
}
