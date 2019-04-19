package DominioDoProblema;

import java.awt.List;
import javax.swing.JOptionPane;

public class Mesa {
    //global
    protected Jogador jogador1;
    protected Jogador jogador2;
    private DisplayMesa display;
    protected AtorJogador jogo;
    protected boolean partidaEmAndamento;
    protected boolean conectado;
    boolean lancouDados = false; //esta variavel define se vou verificar dados ao enviar jogada ou ao receber jogada
    protected int total = 0;
    protected int totalApostas = 0;
    protected int totalApostasJogador2 = 0;
    protected int saldo = 100;
    protected int armazenaSomaDados = 0;
    protected int contaClick = 0;
    protected int contaJogada = 1;
    protected int totalApostas4 = 0;
    protected int totalApostas5 = 0;
    protected int totalApostas6 = 0;
    protected int totalApostas8 = 0;
    protected int totalApostas9 = 0;
    protected int totalApostas10 = 0;
    protected int totalApostasCome = 0;
    protected int totalApostasDontCome = 0;
    protected int totalApostasPass = 0;
    protected int totalApostasDontPass = 0;
    protected int totalApostasField = 0;
    protected List apostasJogador1;
    protected List apostasJogador2;
    protected int totalApostasJogador1;
    protected int resultadoRodada = 0; //lost, win or continue
    protected int somaDasFacesDados = 0;
    protected int faceDado1 = 0;
    protected int faceDado2 = 0;
    protected int point = 0;
    protected int vitoriasJogador1 = 0;
    protected int derrotasJogador1 = 0;
    protected int vitoriasJogador2 = 0;
    protected int derrotasJogador2 = 0;

    public Mesa(DisplayMesa displ) {
        display = displ;
    }

    public Lance informarJogada(int dado1,
                                int dado2,
                                int somaDados,
                                int vitoriasJogador1,
                                int vitoriasJogador2,
                                int derrotasJogador1,
                                int derrotasJogador2,
                                int totalApostasJogador1,
                                int totalApostasJogador2,
                                int point,
                                List informarApostasJogador1,
                                List informarApostasJogador2,
                                int contaJogada,
                                int resultadoRodada) {
        Lance lance = new Lance();
        lance.assumir(dado1,
            dado2,
            somaDados,
            vitoriasJogador1,
            vitoriasJogador2,
            derrotasJogador1,
            derrotasJogador2,
            totalApostasJogador1,
            totalApostasJogador2,
            point,
            informarApostasJogador1,
            informarApostasJogador2,
            contaJogada,
            resultadoRodada);
        return lance;
    }

    public void esvaziar() {
        jogador1 = null;
        jogador2 = null;
        partidaEmAndamento = false;
    }

    public boolean lancouDados() {
        return lancouDados;
    }

    public void criarJogador(String idJogador) {
        if (jogador1 == null) {
            jogador1 = new Jogador(display);
            jogador1.iniciar();
            jogador1.assumirNome(idJogador);
            jogador1.daVez = true;
        } else {
            jogador2 = new Jogador(display);
            jogador2.iniciar();
            jogador2.assumirNome(idJogador);
            jogador2.daVez = false;
            display.lancarButton.setEnabled(false);
        }
    }

    public void habilitar(Integer posicao) {
        partidaEmAndamento = true;
        if (posicao == 1) {
            jogador1.habilitar();
            jogador2.desabilitar();
        } else {
            jogador2.habilitar();
            jogador1.desabilitar();
        }
    }

    public void receberJogada(int informarDado1,
                              int informarDado2,
                              int informarSomaDados,
                              int informarTotalApostasJogador1,
                              int informarTotalApostasJogador2,
                              int informarPoint,
                              List informarApostasJogador1,
                              List informarApostasJogador2,
                              int contaJogada,
                              int resultadoRodada) {

        faceDado1 = informarDado1;
        faceDado2 = informarDado2;
        somaDasFacesDados = informarSomaDados;
        totalApostasJogador1 = informarTotalApostasJogador1;
        totalApostasJogador2 = informarTotalApostasJogador2;
        point = informarPoint;
        apostasJogador1 = informarApostasJogador1;
        apostasJogador2 = informarApostasJogador2;
        contaJogada = contaJogada;
        resultadoRodada = resultadoRodada;

        //Atualização da interface
        display.totalApostasJogador2TextField.setText("" + totalApostasJogador1);
        display.dado1TextField.setText("" + informarDado1);
        display.dado2TextField.setText("" + informarDado1);
        display.somaDasFacesTextField.setText("" + somaDasFacesDados);
        display.vitoriasJogador1TextField.setText("" + vitoriasJogador1);
        display.derrotasJogador1TextField.setText("" + derrotasJogador1);
    }

    public void atualizarApostasDoAdversario() {
        display.apostasJogador2List.removeAll();
        //adiciona todas as apostas do jogador adversario no display atual
        for (int i = 0; i <= (display.apostasJogador1List.countItems() + 10); i++) {
            display.apostasJogador2List.add(apostasJogador1.getItem(i));
        }
    }

    //limpa todas as apostas menos PASS LINE e DONT PASS LINE
    //tem que executar toda vez que for verificado uma aposta
    public void zerarApostasNaoFixas() {
        if (totalApostas4 > 0) {
            display.apostasJogador1List.remove("(4) : " + (totalApostas4) + " fichas");
        }
        if (totalApostas5 > 0) {
            display.apostasJogador1List.remove("(5) : " + (totalApostas5) + " fichas");
        }
        if (totalApostas6 > 0) {
            display.apostasJogador1List.remove("(6) : " + (totalApostas6) + " fichas");
        }
        if (totalApostas8 > 0) {
            display.apostasJogador1List.remove("(8) : " + (totalApostas8) + " fichas");
        }
        if (totalApostas9 > 0) {
            display.apostasJogador1List.remove("(9) : " + (totalApostas9) + " fichas");
        }
        if (totalApostas10 > 0) {
            display.apostasJogador1List.remove("(10) : " + (totalApostas10) + " fichas");
        }
        if (totalApostasCome > 0) {
            display.apostasJogador1List.remove("COME: " + (totalApostasCome) + " fichas");
        }
        if (totalApostasDontCome > 0) {
            display.apostasJogador1List.remove("DONT COME: " + (totalApostasDontCome) + " fichas");
        }
        if (totalApostasField > 0) {
            display.apostasJogador1List.remove("FIELD: " + totalApostasField + " fichas");
        }
        totalApostas4 = 0;
        totalApostas5 = 0;
        totalApostas6 = 0;
        totalApostas8 = 0;
        totalApostas9 = 0;
        totalApostas10 = 0;
        totalApostasCome = 0;
        totalApostasDontCome = 0;
        totalApostasField = 0;
    }

    //Clear apostas do Jogador
    public void zerarApostasJogadores() {
        display.apostasJogador1List.removeAll();
        display.apostasJogador2List.removeAll();
        display.totalApostasJogador1TextField.setText(" 0 ficha(s)");
        display.totalApostasJogador2TextField.setText(" 0 ficha(s)");
        totalApostas = 0;
        totalApostas4 = 0;
        totalApostas5 = 0;
        totalApostas6 = 0;
        totalApostas8 = 0;
        totalApostas9 = 0;
        totalApostas10 = 0;
        totalApostasCome = 0;
        totalApostasDontCome = 0;
        totalApostasPass = 0;
        totalApostasDontPass = 0;
        totalApostasField = 0;
        contaClick = 0;
        desabilitarBotoesDeApostas();
    }

    public void habilitadorJogada1() {
        display.passLineButton.setVisible(true);
        display.passLineVerticalButton.setVisible(true);
        display.dontPassButton.setVisible(true);
        display.dontPassVerticalButton.setVisible(true);
    }

    public void habilitadorJogada2() {
        display.quatroButton.setVisible(true);
        display.cincoButton.setVisible(true);
        display.seisButton.setVisible(true);
        display.oitoButton.setVisible(true);
        display.noveButton.setVisible(true);
        display.dezButton.setVisible(true);
        display.fieldButton.setVisible(true);
    }

    public void habilitadorJogada3() {
        display.comeButton.setVisible(true);
        display.dontComeButton.setVisible(true);
    }

    public void habilitarBotoes() {
        if (contaJogada == 1) {
            habilitadorJogada1();
        } else if (contaJogada == 2) {
            habilitadorJogada2();
        } else if (contaJogada >= 3) {
            habilitadorJogada3();
        }
    }

    public void desabilitarBotoesDeApostas() {
        display.quatroButton.setVisible(false);
        display.cincoButton.setVisible(false);
        display.seisButton.setVisible(false);
        display.oitoButton.setVisible(false);
        display.noveButton.setVisible(false);
        display.dezButton.setVisible(false);
        display.fieldButton.setVisible(false);
        display.comeButton.setVisible(false);
        display.dontComeButton.setVisible(false);
    }

    public void habilitarShooter() {
        display.lancarButton.setEnabled(true);
        display.confirmarApostasButton.setEnabled(false);
    }

    public void desabilitarShooter() {
        display.lancarButton.setEnabled(false);
        display.confirmarApostasButton.setEnabled(true);
    }

    public void descontaSaldo() {
        if (saldo > 0) {
            --saldo;        //set o novo valor de saldo
            ++totalApostas;
            display.saldotextField.setText(toStringSaldo() + " fichas");    //exibe na tela o saldo atualizado
            display.totalApostasJogador1TextField.setText(toStringTotalApostas());
        } else {
            informarSaldoInsuficiente();
        }
    }

    public void informarSaldoInsuficiente() {
        int resultado = 13;
        display.notificarResultado(resultado);
    }

    //logica das apostas
    public void verificarApostasEm4() {
        if (totalApostas4 > 0) {        //se foi apostado em 4 e a somaDasFacesDados == 4 PAGUE
            if (toStringSomaDasFacesDados().equals(4)) {
                saldo = saldo + totalApostas4 * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\n\nGanhou:  " + totalApostas4 * 2 +
                    " fichas \n por ter apostado " + totalApostas4 + " ficha(s) no 4. ");
            }
        }
    }

    public void verificarApostasEm5() {
        if (totalApostas5 > 0) {
            if (somaDasFacesDados == 5) {
                saldo = saldo + totalApostas5 * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou:  "
                    + totalApostas5 * 2 + " fichas \n por ter apostado " + totalApostas5 + " ficha(s) no 5. ");
            }
        }
    }

    public void verificarApostasEm6() {
        if (totalApostas6 > 0) {
            if (somaDasFacesDados == 6) {
                saldo = saldo + totalApostas6 * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou:  "
                    + totalApostas6 * 2 + " fichas \n por ter apostado " + totalApostas6 + " ficha(s) no 6. ");
            }
        }
    }

    public void verificarApostasEm8() {
        if (totalApostas8 > 0) {
            if (somaDasFacesDados == 8) {
                saldo = saldo + totalApostas8 * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou: "
                    + totalApostas8 * 2 + " fichas \n por ter apostado " + totalApostas8 + " ficha(s) no 8. ");
            }
        }
    }

    public void verificarApostasEm9() {
        if (totalApostas9 > 0) {
            if (somaDasFacesDados == 9) {
                saldo = saldo + totalApostas9 * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou:  "
                    + totalApostas9 * 2 + "fichas \n por ter apostado " + totalApostas9 + " ficha(s) no 9. ");

            }
        }
    }

    public void verificarApostasEm10() {
        if (totalApostas10 > 0) {        //se foi apostado em 4 e a somaDasFacesDados == 4 PAGUE
            if (somaDasFacesDados == 10) {
                saldo = saldo + totalApostas10 * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou:   "
                    + totalApostas10 * 2 + " fichas \n por ter apostado " + totalApostas10 + " ficha(s) no 10. ");
            }
        }
    }

    public void verificarApostasEmCome() {
        if (totalApostasCome > 0) {
            if ((somaDasFacesDados == resultadoRodada || somaDasFacesDados == armazenaSomaDados)
                && (armazenaSomaDados != 7 || armazenaSomaDados != 11)) {
                saldo = saldo + totalApostasCome * 2;
                display.saldotextField.setText(toStringSaldo());
                JOptionPane.showMessageDialog(display, "     JOGADA\n\nGanhou:   " + totalApostasCome * 2 +
                    " fichas \n por ter apostado " + totalApostasCome + " ficha(s) no COME. ");
            }
        }
    }

    public void verificarApostasEmDontCome() {
        if (totalApostasDontCome > 0) {
            if ((somaDasFacesDados != armazenaSomaDados && (armazenaSomaDados != 7 || armazenaSomaDados != 11))) {
                saldo = saldo + totalApostasDontCome * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou:   " + totalApostasDontCome * 2 +
                    "fichas\n por ter apostado " + totalApostasDontCome + " ficha(s) no DONT COME. ");
            }
        }
    }

    public void verificarApostaEmPassLine() {
        if (totalApostasPass > 0) {
            if (somaDasFacesDados == 7 || somaDasFacesDados == 11) {
                saldo = saldo + totalApostasPass * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\n\nGanhou:   " + totalApostasPass * 2 +
                    " fichas \n por ter apostado " + totalApostasPass + " ficha(s) no PASS LINE");
                display.apostasJogador1List.removeAll();
                totalApostas = 0;
                totalApostasPass = 0;
                display.totalApostasJogador1TextField.setText(" 0 ficha(s)");
            }
        }
    }

    public void verificarApostaEmDontPassLine() {
        if (totalApostasDontPass > 0) {
            if ((somaDasFacesDados == 2 || somaDasFacesDados == 3 || somaDasFacesDados == 12)) {
                saldo = saldo + totalApostasDontPass * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\n\nGanhou:  " + totalApostasDontPass * 2 +
                    " fichas \n por ter apostado " + totalApostasDontPass + " ficha(s) no DONT PASS LINE. ");
                display.apostasJogador1List.removeAll();
                totalApostas = 0;
                totalApostasDontPass = 0;
                display.totalApostasJogador1TextField.setText(" 0 ficha(s)");
            }
        }
    }

    public void verificarApostaEmField() {
        if (totalApostasField > 0) {

            if (somaDasFacesDados == 2) {
                saldo = saldo + totalApostasField * 2;
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou o DOBRO do valor apostado: " + totalApostasField * 2 +
                    " fichas \n por ter apostado " + totalApostasField + " ficha(s) no FIELD (soma das faces = 2). ");
            }

            if (somaDasFacesDados == 12) {
                saldo = saldo + totalApostasField * 3;
                display.saldotextField.setText(toStringSaldo());
                JOptionPane.showMessageDialog(display, "JOGADA\nganhou: o TRIPO do valor apostado: " + totalApostasField * 3 +
                    " fichas \n por ter apostado " + totalApostasField + " ficha(s) no FIELD (soma das faces = 12). ");
            }

            if (somaDasFacesDados == 3 || somaDasFacesDados == 4 || somaDasFacesDados == 9
                || somaDasFacesDados == 10 || somaDasFacesDados == 11) {
                saldo = (saldo + totalApostasField);
                display.saldotextField.setText(toStringSaldo() + "ficha(s)");
                JOptionPane.showMessageDialog(display, "JOGADA  (teste de sorte)\nO casino devolveu a sua aposta no valor de "
                    + totalApostasField + " fichas, por ter apostado " + totalApostasField +
                    " ficha(s) no FIELD (soma das faces = 3 ou 4 ou 9 ou 10 ou 11). ");
            }
        }
    }

    public void verificarTodasApostas() {
        verificarApostaEmPassLine();
        verificarApostaEmDontPassLine();
        verificarApostasEmCome();
        verificarApostasEmDontCome();
        verificarApostaEmField();
        verificarApostasEm4();
        verificarApostasEm5();
        verificarApostasEm6();
        verificarApostasEm8();
        verificarApostasEm9();
        verificarApostasEm10();
        zerarApostasNaoFixas();
    }

    public void conferirTodosResultados() {
        //verifica rodada e verifica jogada - somente executa quando for LANCAR DADOS
        lancouDados();
        if (lancouDados == true) {  //o jogador atual eh o shooter (lancador)
            verificarTodasApostas();
            verificarRodada(resultadoRodada);
            desabilitarShooter();
        }
    }

    public void atualizarVitorias() {
        ++vitoriasJogador1;
        display.vitoriasJogador1TextField.setText("  " + vitoriasJogador1);
    }

    public void atualizarDerrotas() {
        ++derrotasJogador1;
        display.derrotasJogador1TextField.setText("  " + derrotasJogador1);
    }

    public int verificaPointAtual() {
        return somaDasFacesDados;
    }

    public boolean informarConectado() {
        return conectado;
    }

    public void estabelecerConectado(boolean valor) {
        conectado = valor;
    }

    public boolean informarEmAndamento() {
        return partidaEmAndamento;
    }

    public void verificarRodada(int valorRodada) {
        int resultado;
        if (valorRodada > 0) {
            atualizarVitorias();
            //UPDATE in  variable
            saldo = (saldo + totalApostas) + totalApostas;    //(saldo + totalAposta) return saldo started
            display.saldotextField.setText(toStringSaldo());
            display.atualizacaoStatusVitoria(2);
            if (somaDasFacesDados == 7 || somaDasFacesDados == 11) {// informa ao jogador como ele ganhou, se por point ou por natural
                resultado = 10;
            } else {
                resultado = 14;
            }
            finalizarPartida();
        } else if (valorRodada < 0) {
            atualizarDerrotas();
            display.atualizacaoStatusVitoria(2);
            resultado = 11;
            finalizarPartida();
        } else {    //POINT
            ++contaJogada;
            display.atualizacaoStatusVitoria(4);
            resultado = 8;
        }
        display.notificarResultado(resultado);
    }

    public void finalizarPartida() {
        partidaEmAndamento = false;
        contaJogada = 1;
        resultadoRodada = 0; //lost, win or continue
        faceDado1 = 0;
        faceDado2 = 0;
        point = 0;
        display.dado1TextField.setText("");
        display.dado2TextField.setText("");
        display.somaDasFacesTextField.setText("");
        display.pointText.setText("");
        display.apostasJogador1List.clear();
        display.apostasJogador2List.clear();
        display.totalApostasJogador1TextField.setText("");
        display.totalApostasJogador2TextField.setText("");
        display.atualizacaoStatusPartida(5);
        display.atualizacaoStatusVitoria(6);
        display.notificarResultado(9);
        zerarApostasJogadores();

        if (vitoriasJogador1 > vitoriasJogador2) {
            jogador1.assumirVencedor();
        } else {
            if (vitoriasJogador2 > vitoriasJogador1) {
                jogador2.assumirVencedor();
            }
        }
        if (saldo < 0) {
            JOptionPane.showMessageDialog(display, "FIM DO JOGO");
        }
    }

    //toStrings
    public String toStringTotalApostas() {
        return "" + totalApostas;
    }

    public String toStringArmazenaSomaDados() {
        return "" + armazenaSomaDados;
    }

    public String toStringSaldo() {
        return "" + saldo;
    }

    public String toStringSomaDasFacesDados() {
        return "" + somaDasFacesDados;
    }

    public String toStringDado1() {
        return "" + faceDado1;
    }

    public String toStringDado2() {
        return "" + faceDado2;
    }

    public String toStringPoint() {
        return "" + point;
    }
}
