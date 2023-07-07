package sistema.telas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import sistema.navegacao.Navegador;


/**
 * A classe Inicio representa a tela inicial do sistema.
 */

public class Inicio extends JPanel {
	
	private static final long serialVersionUID = 1L;

	
	JLabel labelTitulo;
	JLabel labelDescricao;
	Image imagemFundo;
	
	/**
	 * Cria uma nova instância da classe Inicio.
	 */
	
	public Inicio() {
		criarComponentes();
		criarEventos();
		carregarImagemFundo();
		Navegador.habilitarMenu();
	}
	
	private void criarComponentes() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		labelTitulo = new JLabel("Escolha uma opção no menu superior", JLabel.CENTER);
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));

		add(labelTitulo);
		
		setPreferredSize(new Dimension(800, 600));
		setVisible(true);
		
	}
	
	private void criarEventos() {
		
	}
	
	private void carregarImagemFundo() {
		try {
			imagemFundo = ImageIO.read(new File("C:\\Users\\Cliente\\Downloads\\imagemProjeto\\icomp.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagemFundo != null) {
			g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
		}
	}

}
