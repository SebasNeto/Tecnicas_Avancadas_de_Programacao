package sistema.navegacao;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Classe principal que inicia o sistema e cria os componentes da interface.
 */

public class Sistema {
	
    /**
     * Painel que representa a tela atual do sistema.
     */
	
	public static JPanel tela;
	
    /**
     * Janela principal do sistema.
     */
	
	public static JFrame frame;
	
    /**
     * Método de inicialização do sistema.
     * @param args argumentos de linha de comando
     */
	
	public static void main(String[]args) {criarComponentes();}
	
    /**
     * Cria os componentes da interface do sistema.
     */
	
	
	private static void criarComponentes() {
		frame = new JFrame("Sistema");
		frame.setSize(700,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		Navegador.login();
	}

}
