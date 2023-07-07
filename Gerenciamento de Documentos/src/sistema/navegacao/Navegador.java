package sistema.navegacao;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JList;

import sistema.telas.Inicio;
import sistema.telas.Login;

import entidade.Cargo;
import sistema.telas.CargosEditar;
import sistema.telas.CargosConsultar;
import sistema.telas.CargosInserir;
import sistema.telas.RelatorioCargos;

import entidade.Funcionario;
import sistema.telas.RelatorioSalarios;
import sistema.telas.FuncionariosConsultar;
import sistema.telas.FuncionariosInserir;
import sistema.telas.FuncionariosEditar;

import entidade.Documentos;
import sistema.telas.DocumentosConsultar;
import sistema.telas.DocumentosEditar;
import sistema.telas.DocumentosInserir;
import sistema.telas.RelatorioDocumentos; 
import sistema.telas.RelatorioDocumentosInteressado;
import sistema.telas.RelatorioDocumentosPalavraChave;

public class Navegador {
	
	@SuppressWarnings("unused")
    private static JFrame janelaPrincipal;
	
	@SuppressWarnings("unused")
    private static Connection conexaoBanco;
    
    @SuppressWarnings("unused")
    private static JList<Documentos> listaDocumentos;

    public static void setJanelaPrincipal(JFrame janela) {
        janelaPrincipal = janela;
    }
    public static void setConexaoBanco(Connection conexao) { 
        conexaoBanco = conexao;
    }

	private static boolean menuConstruido;
	private static boolean menuHabilitado;
	private static JMenuBar menuBar;
	private static JMenu menuArquivo, menuFuncionarios, menuCargos, menuRelatorios;
	private static JMenuItem miSair, miFuncionariosConsultar, miFuncionariosCadastrar, miCargosConsultar;
	private static JMenuItem miCargosCadastrar, miRelatoriosCargos, miRelatoriosSalarios;
	
	private static JMenu menuDocumentos;
	private static JMenuItem miDocumentosConsultar;
	private static JMenuItem miDocumentosInserir;
	

    
	public static void login() {
		Sistema.tela = new Login();
		Sistema.frame.setTitle(" Funcionarios ICOMP ");
		Navegador.atualizarTela();
	}
	
	public static void inicio() {
		Sistema.tela = new Inicio();
		Sistema.frame.setTitle(" Funcionarios ICOMP ");
		Navegador.atualizarTela();
	}
	
	public static void cargosCadastrar() {
		Sistema.tela = new CargosInserir();
		Sistema.frame.setTitle("Funcionarios ICOMP - Cadastrar Cargos");
		Navegador.atualizarTela();		
	}
	
	public static void cargosConsultar() {
		Sistema.tela = new CargosConsultar();
		Sistema.frame.setTitle("Funcionarios ICOMP - Consultar Cargos");
		Navegador.atualizarTela();	
	}
	
	public static void cargosEditar(Cargo cargo) {
		Sistema.tela = new CargosEditar(cargo);
		Sistema.frame.setTitle("Funcionarios ICOMP - Editar Cargos");
		Navegador.atualizarTela();
	}
	
	 public static void funcionariosCadastrar(){
		 Sistema.tela = new FuncionariosInserir();
	     Sistema.frame.setTitle("Funcionarios ICOMP - Cadastrar funcionarios");
	     Navegador.atualizarTela();
	 }
	    
	 public static void funcionariosConsultar(){
	     Sistema.tela = new FuncionariosConsultar();
	     Sistema.frame.setTitle("Funcionarios ICOMP - Consultar funcionarios");
	     Navegador.atualizarTela();
	 }
	 
	 public static void funcionariosEditar(Funcionario funcionario){
	     Sistema.tela = new FuncionariosEditar(funcionario);  
	     Sistema.frame.setTitle("Funcionarios ICOMP - Editar funcionarios");           
	     Navegador.atualizarTela();
	    }
	 
	    public static void documentosCadastrar() { 
	        Sistema.tela = new DocumentosInserir();
	        Sistema.frame.setTitle("Funcionarios ICOMP - Cadastrar Documentos");
	        Navegador.atualizarTela();
	    }
	    
	    
	    public static void documentosEditar(Documentos documento) { 
	        Sistema.tela = new DocumentosEditar(documento);
	        Sistema.frame.setTitle("Funcionarios ICOMP - Editar Documento");
	        Navegador.atualizarTela();
	    }	   
	    

	    public static void documentosConsultar() { 
	        Sistema.tela = new DocumentosConsultar();
	        Sistema.frame.setTitle("Funcionarios ICOMP - Consultar Documentos");
	        Navegador.atualizarTela();
	    }
	 
	 public static void relatoriosCargos(){   
	        Sistema.tela = new RelatorioCargos();
	        Sistema.frame.setTitle("Funcionarios ICOMP - Relatarios");    
	        Navegador.atualizarTela();
	    }
	 
	    
	    public static void relatoriosSalarios(){
	        Sistema.tela = new RelatorioSalarios();
	        Sistema.frame.setTitle("Funcionarios ICOMP - Relatarios");    
	        Navegador.atualizarTela();
	    }
	    
	    public static void relatoriosDocumentos() {
	        Sistema.tela = new RelatorioDocumentos();
	        Sistema.frame.setTitle("Funcionarios ICOMP - Relatórios");
	        Navegador.atualizarTela();
	    }
	    
	    public static void relatoriosDocumentosInteressado() {
	        Sistema.tela = new RelatorioDocumentosInteressado();
	        Sistema.frame.setTitle("Funcionarios ICOMP - Relatórios de Documentos por Interessado");
	        Navegador.atualizarTela();
	    }
	    
	    public static void relatoriosDocumentosPalavraChave() {
	        Sistema.tela = new RelatorioDocumentosPalavraChave();
	        Sistema.frame.setTitle("Funcionarios ICOMP - Relatórios de Documentos por Interessado");
	        Navegador.atualizarTela();
	    }
	    
	
	private static void atualizarTela() {
		Sistema.frame.getContentPane().removeAll();
		Sistema.tela.setVisible(true);
		Sistema.frame.add(Sistema.tela);
		Sistema.frame.setVisible(true);
	}
	
	
	private static void construirMenu() {
		if(!menuConstruido) {
			menuConstruido = true;
			
			menuBar = new JMenuBar();
			
			menuArquivo = new JMenu("Arquivo");
			menuBar.add(menuArquivo);
			miSair = new JMenuItem("Sair");
			menuArquivo.add(miSair);
			
			menuFuncionarios = new JMenu ("Funcionarios");
			menuBar.add(menuFuncionarios);
			miFuncionariosCadastrar = new JMenuItem("Cadastrar");
			menuFuncionarios.add(miFuncionariosCadastrar);
			miFuncionariosConsultar = new JMenuItem("Consultar");
			menuFuncionarios.add(miFuncionariosConsultar);
			
			menuCargos = new JMenu("Cargos");
			menuBar.add(menuCargos);
			miCargosCadastrar = new JMenuItem("Cadastrar");
			menuCargos.add(miCargosCadastrar);
			miCargosConsultar = new JMenuItem("Consultar");
			menuCargos.add(miCargosConsultar);
			
			menuRelatorios = new JMenu("Relatorios");
			menuBar.add(menuRelatorios);
			miRelatoriosCargos = new JMenuItem("Funcionarios por Cargos");
			menuRelatorios.add(miRelatoriosCargos);
			miRelatoriosSalarios = new JMenuItem("Salarios dos Funcionarios");
			menuRelatorios.add(miRelatoriosSalarios);
			
	        menuDocumentos = new JMenu("Documentos");
	        menuBar.add(menuDocumentos);
	        miDocumentosConsultar = new JMenuItem("Consultar");
	        menuDocumentos.add(miDocumentosConsultar);
	        miDocumentosInserir = new JMenuItem("Inserir");
	        menuDocumentos.add(miDocumentosInserir);
	      
			
			criarEventosMenu();
		}
	}
	
	public static void habilitarMenu() {
		if(!menuConstruido) construirMenu();
		if(!menuHabilitado) {
			menuHabilitado = true;
			Sistema.frame.setJMenuBar(menuBar);
		}
	}
	
	public static void desabilitarMenu() {
		if(menuHabilitado) {
			menuHabilitado = false;
			Sistema.frame.setJMenuBar(null);
		}
	}
	
	private static void criarEventosMenu() {
		miSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		miFuncionariosCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funcionariosCadastrar();
			}
		});
		miFuncionariosConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				funcionariosConsultar();
			
			}
		});
		
		miCargosCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosCadastrar();
			}
		});
		
		miCargosConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargosConsultar();
			}
		});
		
		miRelatoriosCargos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relatoriosCargos();
			}
		});
		
		miRelatoriosSalarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				relatoriosSalarios();
			}
		});
		
        miDocumentosConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                documentosConsultar();
            }
        });
        

        miDocumentosInserir.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                documentosCadastrar();
            }
        });
        
		
        JMenuItem miRelatoriosDocumentos = new JMenuItem("Relatórios de Documentos por Tipo"); 
        
        miRelatoriosDocumentos.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosDocumentos();
            }
        });
        
        menuRelatorios.add(miRelatoriosDocumentos);
        

        JMenuItem miRelatoriosDocumentosInteressado = new JMenuItem("Relatórios de Documentos por Interessado");

        miRelatoriosDocumentosInteressado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                relatoriosDocumentosInteressado();
            }
        });

        menuRelatorios.add(miRelatoriosDocumentosInteressado);
        

        JMenuItem miRelatoriosDocumentosPalavraChave = new JMenuItem("Relatórios de Documentos por Referência");

        miRelatoriosDocumentosPalavraChave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	relatoriosDocumentosPalavraChave();
            }
        });

        menuRelatorios.add(miRelatoriosDocumentosPalavraChave);
        
	}

}
