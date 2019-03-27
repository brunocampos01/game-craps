package DominioDoProblema;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import java.awt.Toolkit;

public class DisplayMesa extends JFrame {
	private static final long serialVersionUID = 1L;
	protected AtorJogador jogo;
	protected Jogador jogador;
	protected Mesa mesa = new Mesa(this);
	protected DisplayDados displayDados = new DisplayDados(this, mesa);
	protected Regra regra = new Regra();
	protected boolean emRede = false;
	protected JPanel jContentPane = null;
    protected Font displayFont = new Font("Monospaced", Font.BOLD, 16);

    private static final String IMAGES = loadResourceFile();

	protected JLabel imagemMesaLabel,
			dado1Label,
			dado2Label,
			maisLabel,
			igualLabel,
			vitoriasJogador1Label,
			derrotasJogador1Label,
			pointLabel,
			somaDasFacesLabel,
			saldoLabel,
			totalDeApostasLabel,
			apostasJogador1Label,
			apostasJogador2Label,
			totalDeApostasJogador1Label,
			totalDeApostasJogador2Label,
			statusLabel,
			statusVitoriaLabel,
			imagemFundoTelaLabel,
			jogador1Label,
			jogador2Label;

	protected JButton confirmarApostasButton,
			quatroButton,
			lancarButton,
			cincoButton,
			seisButton,
			oitoButton,
			noveButton,
			dezButton,
			comeButton,
			dontComeButton,
			passLineButton,
			dontPassButton,
			dontPassVerticalButton,
			passLineVerticalButton,
			fieldButton;

	protected List apostasJogador1List, apostasJogador2List;

	protected JTextField vitoriasJogador1TextField,
			derrotasJogador1TextField,
			pointText,
			saldotextField,
			dado1TextField,
			dado2TextField,
			somaDasFacesTextField,
			totalApostasJogador1TextField,
			totalApostasJogador2TextField,
			vitoriasJogador2TextField,
			derrotasJogador2TextField;

	protected JMenuBar jMenuBar1 = null;
	protected JMenu menuJogo = null;
	protected JMenuItem jMenuItem1 = null;
	protected JMenuItem jMenuItem2 = null;
	protected JMenuItem jMenuItem3 = null;
	protected JMenuItem jMenuItem4 = null;

	public DisplayMesa() throws HeadlessException {
            super();
            setIconImage(Toolkit.getDefaultToolkit()
                .getImage("imagens/dados.jpg"));
            initialize();
	}

	public DisplayMesa(DisplayDados displayDados) {
            super();
            initialize();
	}

	private void initialize() {
            this.setSize(1190, 802);
            this.setContentPane(getJContentPane());
            this.setTitle("CRAPS");
            jogo = new AtorJogador(this, mesa, displayDados);
            regra = new Regra();
	}

    private static String loadResourceFile() {
        try  {
            InputStream is = MysqlHelper.class.getClassLoader().getResourceAsStream(SCHEMA_RESOURCE);
            StringWriter writer = new StringWriter();
            IOUtils.copy(is, writer, StandardCharsets.UTF_8);
            return writer.toString();
        } catch (IOException e) {
            logger.fatal("could not load resource file", e);
        }
        return null;
    }

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jMenuBar1 = new JMenuBar();
			jMenuBar1.add(getMenu());
            this.setJMenuBar(jMenuBar1);

		//JLabel
		imagemMesaLabel = new JLabel("");
		imagemMesaLabel.setBounds(0, 0, 728, 454);
		imagemMesaLabel.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(102, 102, 51)));
		imagemMesaLabel.setIcon(new ImageIcon("/imagens/craps 708x477.png"));

		statusLabel = new JLabel("Status do jogo");
		statusLabel.setForeground(new Color(255, 255, 255));
		statusLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		statusLabel.setBounds(740, 0, 408, 51);

		statusVitoriaLabel = new JLabel("");
		statusVitoriaLabel.setForeground(new Color(255, 255, 255));
		statusVitoriaLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		statusVitoriaLabel.setBounds(740, 0, 408, 100);

		dado1Label = new JLabel("Dado 1");
		dado1Label.setForeground(new Color(255, 255, 255));
		dado1Label.setBounds(959, 555, 56, 16);
		dado2Label = new JLabel("Dado 2");
		dado2Label.setForeground(new Color(255, 255, 255));
		dado2Label.setBounds(1028, 555, 56, 16);

		maisLabel = new JLabel("+");
		maisLabel.setForeground(new Color(255, 255, 255));
		maisLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		maisLabel.setBounds(995, 584, 23, 17);

		igualLabel = new JLabel("=");
		igualLabel.setForeground(new Color(255, 255, 255));
		igualLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		igualLabel.setBounds(1054, 584, 23, 16);

		totalDeApostasLabel = new JLabel("Total de Apostas");
		totalDeApostasLabel.setBounds(504, 716, 96, 16);

		totalDeApostasJogador1Label = new JLabel("Total de Apostas");
		totalDeApostasJogador1Label.setForeground(new Color(255, 255, 255));
		totalDeApostasJogador1Label.setBounds(499, 683, 150, 16);

		vitoriasJogador1Label = new JLabel("Vitorias");
		vitoriasJogador1Label.setForeground(new Color(255, 255, 255));
		vitoriasJogador1Label.setBounds(12, 536, 56, 16);

		derrotasJogador1Label = new JLabel("Derrotas");
		derrotasJogador1Label.setForeground(new Color(255, 255, 255));
		derrotasJogador1Label.setBounds(96, 536, 65, 16);

		pointLabel = new JLabel("Point");
		pointLabel.setForeground(Color.WHITE);
		pointLabel.setBounds(197, 536, 56, 16);
        somaDasFacesLabel = new JLabel("soma das faces");
		somaDasFacesLabel.setBounds(1090, 555, 99, 16);

		saldoLabel = new JLabel("Saldo");
		saldoLabel.setForeground(new Color(255, 255, 255));
		saldoLabel.setBounds(340, 683, 56, 16);

		totalDeApostasJogador2Label = new JLabel("Total de Apostas");
		totalDeApostasJogador2Label.setForeground(new Color(255, 255, 255));
		totalDeApostasJogador2Label.setBounds(710, 683, 120, 16);

		apostasJogador2Label = new JLabel("Apostas jogador 1");
		apostasJogador2Label.setForeground(new Color(255, 255, 255));
		apostasJogador2Label.setBounds(484, 536, 147, 16);

		apostasJogador1Label = new JLabel("Apostas jogador 2");
		apostasJogador1Label.setForeground(new Color(255, 255, 255));
		apostasJogador1Label.setBounds(689, 536, 154, 16);

        jogador1Label = new JLabel("Jogador 1");
		jogador1Label.setFont(new Font("Tahoma", Font.BOLD, 15));
		jogador1Label.setForeground(Color.WHITE);
		jogador1Label.setBounds(41, 502, 120, 24);

		jogador2Label = new JLabel("Jogador 2");
		jogador2Label.setForeground(Color.WHITE);
		jogador2Label.setFont(new Font("Tahoma", Font.BOLD, 15));
		jogador2Label.setBounds(41, 607, 120, 18);

		displayDados.setBounds(740, 82, 408, 282);

		//List
		this.apostasJogador1List = new List();
		this.apostasJogador1List.setMultipleSelections(true);
		this.apostasJogador1List.setBounds(474, 555, 164, 125);

		this.apostasJogador2List = new List();
		this.apostasJogador2List.setMultipleSelections(true);
		this.apostasJogador2List.setBounds(689, 555, 164, 125);

		//BUTTONS
        //lancarDados
		lancarButton = new JButton("Lancar dados e confirmar apostas");
		lancarButton.setSelectedIcon(new ImageIcon("/imagens/dados.png"));
		lancarButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	lancarButton.addActionListener(this);
                mesa.lancouDados = true;
                if (!displayDados.diceAreRolling()) {
                	displayDados.rollDice();
                }
			}
		});

		lancarButton.setBounds(740, 396, 408, 58);
		lancarButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		lancarButton.addMouseListener(new MouseAdapter() {


		@Override
		public void mouseClicked(MouseEvent clickLancar) {
		}
		});

		//confirmar apostas
		confirmarApostasButton = new JButton("Confirmar Apostas");
        confirmarApostasButton.setBounds(184, 467, 331, 42);
		confirmarApostasButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		confirmarApostasButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
            	statusLabel.setText("Partida em andamento");
                mesa.lancouDados = false;
                jogo.enviarJogada(mesa.faceDado1,
						mesa.faceDado2,
						mesa.somaDasFacesDados,
						mesa.vitoriasJogador1,
						mesa.vitoriasJogador2,
						mesa.derrotasJogador1,
						mesa.derrotasJogador2,
						mesa.totalApostas,
						mesa.totalApostasJogador2,
						mesa.point,
						apostasJogador1List,
						apostasJogador2List,
						mesa.contaJogada,
						mesa.resultadoRodada);
			}
		});

		//APOSTAS
		quatroButton = new JButton("quatroButton");
		quatroButton.setIcon(new ImageIcon("imagens/quatro.png"));
		quatroButton.setBounds(252, 71, 56, 70);
		quatroButton.setVisible(false);
		quatroButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			apostasJogador1List.add("(4) : " +mesa.totalApostas4+ " fichas");
            if(mesa.totalApostas4 > 1){
            	apostasJogador1List.remove("(4) : " +(mesa.totalApostas4 - 1)+ " fichas");
            }
		}
		});

		quatroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	mesa.descontaSaldo();
                ++mesa.totalApostas4;
			}
		});

		cincoButton = new JButton("cincoButton");
		cincoButton.setIcon(new ImageIcon("/imagens/cinco.png"));
		cincoButton.setBounds(329, 71, 56, 70);
		cincoButton.setVisible(false);
		cincoButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			apostasJogador1List.add("(5) : " +mesa.totalApostas5+ " fichas");
            if(mesa.totalApostas5 >1){
            	apostasJogador1List.remove("(5) : " +(mesa.totalApostas5 - 1)+ " fichas");
            }
                }
		});
		cincoButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostas5;
		}
		});

		seisButton = new JButton("seisButton");
		seisButton.setIcon(new ImageIcon("imagens/seis.png"));
		seisButton.setBounds(404, 71, 65, 70);
		seisButton.setVisible(false);
		seisButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("(6) : " +mesa.totalApostas6+ " fichas");
                    if(mesa.totalApostas6 >1){
                        apostasJogador1List.remove("(6) : " +(mesa.totalApostas6 - 1)+ " fichas");
                    }
		}
		});
		seisButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostas6;
                }
		});

		oitoButton = new JButton("oitoButton");
		oitoButton.setIcon(new ImageIcon("imagens/oito.png"));
		oitoButton.setBounds(495, 71, 55, 70);
		oitoButton.setVisible(false);
		oitoButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			apostasJogador1List.add("(8) : " +mesa.totalApostas8+ " fichas");
            if(mesa.totalApostas8 >1){
				apostasJogador1List.remove("(8) : " +(mesa.totalApostas8 - 1)+ " fichas");
            }
		}
		});
		oitoButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostas8;
                }
		});

		noveButton = new JButton("noveButton");
		noveButton.setIcon(new ImageIcon("imagens/nove.png"));
		noveButton.setBounds(562, 71, 67, 70);
		noveButton.setVisible(false);
		noveButton.addMouseListener(new MouseAdapter() {
		@Override
                public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("(9) : " +mesa.totalApostas9+ " fichas");
                        if(mesa.totalApostas9 >1){
                            apostasJogador1List.remove("(9) : " +(mesa.totalApostas9 - 1)+ " fichas");
                        }
		}
		});
		noveButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostas9;
		}
		});

		dezButton = new JButton("dezButton");
		dezButton.setIcon(new ImageIcon("imagens/dez.png"));
		dezButton.setBounds(640, 71, 65, 70);
		dezButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("(10) : " +mesa.totalApostas10+ " fichas");
                        if(mesa.totalApostas10 >1){
                            apostasJogador1List.remove("(10) : " +(mesa.totalApostas10 - 1)+ " fichas");
			}
                }
		});
		dezButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent events) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostas10;
		}
		});

                //PASS LINE
		passLineButton = new JButton("passLineButton");
		passLineButton.setIcon(new ImageIcon("/imagens/pass line horizontal.png"));
		passLineButton.setBounds(176, 396, 453, 42);
		passLineButton.addMouseListener(new MouseAdapter() {
		@Override
                public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("PASS LINE: " +mesa.totalApostasPass+ " fichas");
                        if(mesa.totalApostasPass >1){
                            apostasJogador1List.remove("PASS LINE: " +(mesa.totalApostasPass - 1)+ " fichas");
			}
                    }
		});
		passLineButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostasPass;
                }
		});

                //PASS LINE VERTICAL
		passLineVerticalButton = new JButton("passLineVerticalButton");
		passLineVerticalButton.setIcon(new ImageIcon("/imagens/pass line vertical.png"));
		passLineVerticalButton.setBounds(75, 101, 34, 205);
		passLineVerticalButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
		apostasJogador1List.add("PASS LINE: " +mesa.totalApostasPass+ " fichas");
                    if(mesa.totalApostasPass >1){
                        apostasJogador1List.remove("PASS LINE: " +(mesa.totalApostasPass - 1)+ " fichas");
                    }
		}
		});
		passLineVerticalButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostasPass;
		}
		});

		//DONT PASS VERTICAL
		dontPassVerticalButton = new JButton("dontPassVerticalButton");
		dontPassVerticalButton.setIcon(new ImageIcon("/imagens/dont pass bar vertical0.png"));
		dontPassVerticalButton.setBounds(121, 55, 34, 138);
		dontPassVerticalButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("DONT PASS: " +mesa.totalApostasDontPass+ " fichas");
                    if(mesa.totalApostasDontPass >1){
                        apostasJogador1List.remove("DONT PASS: " +(mesa.totalApostasDontPass - 1)+ " fichas");
                    }
		}
		});
		dontPassVerticalButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostasDontPass;
		}
		});

		//DONT PASS
		dontPassButton = new JButton("dontPassButton");
		dontPassButton.setIcon(new ImageIcon("/imagens/dont pass bar.png"));
		dontPassButton.setBounds(259, 345, 307, 38);
		dontPassButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("DONT PASS: " +mesa.totalApostasDontPass+ " fichas");
                    if(mesa.totalApostasDontPass >1){
                    	apostasJogador1List.remove("DONT PASS: " +(mesa.totalApostasDontPass - 1)+ " fichas");
                    }
		}
		});
		dontPassButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostasDontPass;
		}
		});

		//COME
		comeButton = new JButton("comeButton");
		comeButton.setIcon(new ImageIcon("/imagens/come.png"));
		comeButton.setBounds(207, 164, 424, 79);
		comeButton.setVisible(false);
		comeButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent clickComeButton) {
                    apostasJogador1List.add("COME: " +mesa.totalApostasCome+ " fichas");
                    if(mesa.totalApostasCome >1){
                        apostasJogador1List.remove("COME: " +(mesa.totalApostasCome - 1)+ " fichas");
                    }
		}
		});
		comeButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostasCome;
                }
		});

		//DONT COME
		dontComeButton = new JButton("dontComeButton");
		dontComeButton.setIcon(new ImageIcon("/imagens/dont come.png"));
		dontComeButton.setBounds(167, 34, 73, 107);
		dontComeButton.setVisible(false);
		dontComeButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("DONT COME: " +mesa.totalApostasDontCome+ " fichas");
                    if(mesa.totalApostasDontCome >1){
                        apostasJogador1List.remove("DONT COME: " +(mesa.totalApostasDontCome - 1)+ " fichas");
                    }
		}
		});
		dontComeButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostasDontCome;
		}
		});

		fieldButton = new JButton("");
		fieldButton.setIcon(new ImageIcon("/imagens/field.png"));
		fieldButton.setBounds(240, 256, 366, 76);
		fieldButton.setVisible(false);
		fieldButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
                    apostasJogador1List.add("FIELD: " +mesa.totalApostasField+ " fichas");
                    if(mesa.totalApostasField >1){
                        apostasJogador1List.remove("FIELD: " +(mesa.totalApostasField - 1)+ " fichas");
                    }
		}
		});
		fieldButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
                    mesa.descontaSaldo();
                    ++mesa.totalApostasField;
		}
		});
                //TextField
                vitoriasJogador1TextField = new JTextField("  0");
                vitoriasJogador1TextField.setBounds(12, 555, 67, 33);
                vitoriasJogador1TextField.setFont(displayFont);
                vitoriasJogador1TextField.setEditable(false);
                vitoriasJogador1TextField.setBackground(Color.WHITE);

                derrotasJogador1TextField = new JTextField("  0");
                derrotasJogador1TextField.setBounds(85, 555, 67, 33);
                derrotasJogador1TextField.setFont(displayFont);
                derrotasJogador1TextField.setEditable(false);
                derrotasJogador1TextField.setBackground(Color.WHITE);

                derrotasJogador2TextField = new JTextField("  0");
                derrotasJogador2TextField.setBounds(85, 658, 67, 33);
                derrotasJogador2TextField.setFont(displayFont);
                derrotasJogador2TextField.setEditable(false);
                derrotasJogador2TextField.setBackground(Color.WHITE);

                vitoriasJogador2TextField = new JTextField("  0");
                vitoriasJogador2TextField.setBounds(12, 658, 67, 33);
                vitoriasJogador2TextField.setFont(displayFont);
                vitoriasJogador2TextField.setEditable(false);
                vitoriasJogador2TextField.setBackground(Color.WHITE);

                pointText = new JTextField(" 0");
                pointText.setBounds(186, 555, 67, 132);
                pointText.setFont(new Font("Monospaced", Font.BOLD, 18));
                pointText.setEditable(false);
                pointText.setBackground(Color.WHITE);

                //saldo
                saldotextField = new JTextField(""+mesa.saldo+" ficha(s)");
                saldotextField.setFont(new Font("Tahoma", Font.BOLD, 13));
                saldotextField.setBounds(316, 701, 91, 22);
                saldotextField.setEditable(false);

                //totalDaAposta
                totalApostasJogador1TextField = new JTextField(""+mesa.totalApostas);
                totalApostasJogador1TextField.setBounds(499, 701, 97, 22);
                totalApostasJogador1TextField.setEditable(false);

                totalApostasJogador2TextField = new JTextField(""+mesa.totalApostasJogador2);
                totalApostasJogador2TextField.setBounds(710, 701, 99, 22);
                totalApostasJogador2TextField.setEditable(false);

                dado1TextField = new JTextField("0");
                dado1TextField.setBounds(969, 584, 13, 24);
                dado1TextField.setEditable(false);

                dado2TextField = new JTextField("0");
                dado2TextField.setBounds(1030, 584, 13, 24);
                dado2TextField.setEditable(false);

                somaDasFacesTextField = new JTextField("0");
                somaDasFacesTextField.setBounds(1083, 584, 34, 24);
                somaDasFacesTextField.setEditable(false);

                //Panel para lancar dados
                jContentPane =  new JPanel();
                jContentPane.setLayout(null);

                //Adicinar os componestes na janela
                jContentPane.add(displayDados, null);
                jContentPane.add(imagemMesaLabel, null);
                jContentPane.add(dado1Label, null);
                jContentPane.add(dado2Label, null);
                jContentPane.add(maisLabel, null);
                jContentPane.add(igualLabel, null);
                jContentPane.add(vitoriasJogador1Label, null);
                jContentPane.add(derrotasJogador1Label, null);
                jContentPane.add(pointLabel, null);
                jContentPane.add(saldoLabel, null);
                jContentPane.add(totalDeApostasJogador2Label, null);
                jContentPane.add(totalDeApostasJogador1Label, null);
                jContentPane.add(apostasJogador1Label, null);
                jContentPane.add(apostasJogador2Label, null);
                jContentPane.add(statusLabel, null);
                jContentPane.add(statusVitoriaLabel, null);
                jContentPane.add(jogador1Label);
                jContentPane.add(vitoriasJogador1TextField, null);
                jContentPane.add(derrotasJogador1TextField, null);
                jContentPane.add(pointText, null);
                jContentPane.add(saldotextField, null);
                jContentPane.add(dado1TextField, null);
                jContentPane.add(dado2TextField, null);
                jContentPane.add(somaDasFacesTextField, null);
                jContentPane.add(totalApostasJogador1TextField, null);
                jContentPane.add(totalApostasJogador2TextField, null);

                jContentPane.add(apostasJogador1List, null);
                jContentPane.add(apostasJogador2List, null);

                jContentPane.add(lancarButton, null);
                jContentPane.add(confirmarApostasButton, null);
                jContentPane.add(quatroButton, null);
                jContentPane.add(cincoButton, null);
                jContentPane.add(seisButton, null);
                jContentPane.add(oitoButton, null);
                jContentPane.add(noveButton, null);
                jContentPane.add(dezButton, null);
                jContentPane.add(comeButton, null);
                jContentPane.add(dontComeButton, null);
                jContentPane.add(dontPassButton, null);
                jContentPane.add(dontPassVerticalButton, null);
                jContentPane.add(passLineButton, null);
                jContentPane.add(passLineVerticalButton);
                jContentPane.add(fieldButton, null);

                imagemFundoTelaLabel = new JLabel("");
                imagemFundoTelaLabel.setIcon(new ImageIcon("//media//brunocampos01//Dados//OneDrive//Computação//Engenharia de Software//Análise e Projetos de Sistemas  - INE5608//trabalho - CRAPS//imagens//fundoVerde.png"));
                imagemFundoTelaLabel.setBounds(0, -318, 1197, 1360);
                jContentPane.add(imagemFundoTelaLabel);

                //inicializa com botoes desabilitados e somente com apostas válidas p a primeira rodada
                mesa.desabilitarBotoesDeApostas();
		}
		return jContentPane;
	}

	private JMenu getMenu() {
            if (menuJogo == null) {
                menuJogo = new JMenu();
                menuJogo.setText("Menu");
                menuJogo.setBounds(new Rectangle(1, 0, 57, 21));

                menuJogo.add(getJMenuItem1());
                menuJogo.add(getJMenuItem2());
                menuJogo.add(getJMenuItem3());
            }
            return menuJogo;
	}

	private JMenuItem getJMenuItem1() {
            if (jMenuItem1 == null) {
                jMenuItem1 = new JMenuItem();
                jMenuItem1.setText("iniciar nova Rodada");
                jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        iniciarPartida();
                    }
                });
            }
            return jMenuItem1;
	}

	private JMenuItem getJMenuItem2() {
            if (jMenuItem2 == null) {
                jMenuItem2 = new JMenuItem();
                jMenuItem2.setText("conectar");
                jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        conectar();
                    }
                });
            }
            return jMenuItem2;
	}

	private JMenuItem getJMenuItem3() {
            if (jMenuItem3 == null) {
                jMenuItem3 = new JMenuItem();
                jMenuItem3.setText("desconectar");
                jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        desconectar();
                    }
                });
            }
            return jMenuItem3;
	}

        //depois do lancamento, este metodo eh invocado
	public void update(int result, int point, int totalSomaDados, int dado1, int dado2) {
            mesa.resultadoRodada = result;
            mesa.point = point;
            mesa.faceDado1 = dado1;
            mesa.faceDado2 = dado2;
            mesa.somaDasFacesDados = totalSomaDados;
            System.out.print("mesa.point= "+mesa.point);
            System.out.print("          mesa.faceDado1= "+mesa.faceDado1);
            System.out.println("        mesa.faceDado2= "+mesa.faceDado2);

            //utilizado para transformar valor int, do proprio method, em String
            dado1TextField.setText(mesa.toStringDado1());
            dado2TextField.setText(mesa.toStringDado2());
            somaDasFacesTextField.setText(mesa.toStringSomaDasFacesDados());
            pointText.setText(""+point);
            ++mesa.contaClick;

            jogo.enviarJogada(mesa.faceDado1, mesa.faceDado2, mesa.somaDasFacesDados, mesa.vitoriasJogador1, mesa.vitoriasJogador2,
                mesa.derrotasJogador1, mesa.derrotasJogador2, mesa.totalApostas, mesa.totalApostasJogador2, mesa.point,
                apostasJogador1List, apostasJogador2List, mesa.contaJogada, mesa.resultadoRodada);
	}

	public void conectar() {
            jContentPane.setVisible(true);
            int resultado = jogo.conectar();
            this.notificarResultado(resultado);
	}

	public void desconectar() {
            int resultado = jogo.desconectar();
            this.notificarResultado(resultado);
	}

	public void iniciarPartida() {
            jContentPane.setVisible(true);
            int resultado = jogo.iniciarPartida();
            this.notificarResultado(resultado);
	}

	public String obterIdJogador() {
            String idJogador = ("jogador");
            idJogador = JOptionPane.showInputDialog(this, ("Insira o nome do jogador"));
            return idJogador;
	}

	public String obterIdServidor() {
            String idServidor = ("localhost");
            idServidor = JOptionPane.showInputDialog(this, ("Insira o endereço do servidor"), idServidor);
            return idServidor;
	}



	public void notificarResultado(int codigo) {
            switch (codigo) {
            case 0:  JOptionPane.showMessageDialog(this, "Conexão efetuada com exito"); break;
            case 1:  JOptionPane.showMessageDialog(this, "Tentativa de conexão com conexão previamente estabelecida"); break;
            case 2:  JOptionPane.showMessageDialog(this, "Tentativa de conexao falhou"); break;
            case 3:  JOptionPane.showMessageDialog(this, "Desconexão efetuada com exito"); break;
            case 4:  JOptionPane.showMessageDialog(this, "Tentativa de desconexao sem conexao previamente estabelecida"); break;
            case 5:  JOptionPane.showMessageDialog(this, "Tentativa de desconexao falhou"); break;
            case 6:  JOptionPane.showMessageDialog(this, "Solicitação de inicio procedida com êxito"); break;
            case 7:  JOptionPane.showMessageDialog(this, "Tentativa de inicio sem conexao previamente estabelecida"); break;
            case 8:  JOptionPane.showMessageDialog(this, "continue");break;
            case 9:  JOptionPane.showMessageDialog(this, "Rodada encerrada"); break;
            case 10: JOptionPane.showMessageDialog(this,"\nRODADA:\n\nVc venceu a RODADA \nSoma dos dados = "+mesa.somaDasFacesDados); break;
            case 11: JOptionPane.showMessageDialog(this,"\nRODADA:\n\nVc perdeu a RODADA \nDeu CRAPS :( Soma dos dados = "+mesa.somaDasFacesDados); break;
            case 12: JOptionPane.showMessageDialog(this,"\nRODADA:\n\nAguarde seu adversário apostar \n:|");break;
            case 13: JOptionPane.showMessageDialog(this,"\nRODADA:\n\nSaldo infucifiente. \n:p");break;
            case 14: JOptionPane.showMessageDialog(this,"\nRODADA:\n\nVc venceu por ter tirado o mesmo valor do lance anterior ("+mesa.somaDasFacesDados+")");break;
            case 15: JOptionPane.showMessageDialog(this,""+jogo.idUsuario+", sua vez de apostar e lancar dados.\n\n              BOA SORTE !"); break;
            };
	}

        public void atualizacaoStatusPartida(int codigo) {
            switch (codigo) {
            case 1:  statusLabel.setText(" Rodada em andamento"); break;
            case 3:  statusLabel.setText(" Rodada em andamento| jogada: "+mesa.contaJogada); break;
            case 5:  statusLabel.setText("Rodada encerrada." ); break;
            };
	}

        public void atualizacaoStatusVitoria(int codigo) {
            switch (codigo) {
            case 2:  statusVitoriaLabel.setText(""); break;
            case 4:  statusVitoriaLabel.setText("Vc Ganha a RODADA se, tirar o mesmo valor nos dados 2X seguidas"); break;
            case 6:  statusVitoriaLabel.setText("Aguarde seu adversário jogar."); break;
            };
	}
}
