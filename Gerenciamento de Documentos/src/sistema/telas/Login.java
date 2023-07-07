package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Graphics;

import sistema.navegacao.Navegador;


/**
 * A classe Login representa a tela de login do sistema.
 */

public class Login extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	JLabel labelUsuario;
	JTextField campoUsuario;
	JLabel labelSenha;
	JPasswordField campoSenha;
	JButton botaoEntrar;
	
	Image imagemFundo;
	
	/**
	 * Cria uma nova instância da classe Login.
	 */
	
	public Login() {
		criarComponentes();
		criarEventos();
		carregarImagemFundo();
		
	}
	
	private void criarComponentes() {
		setLayout(null);
		
		
		JLabel labelImagem = new JLabel();
		labelImagem.setBounds(200, 200, 200, 300); 
		labelImagem.setIcon(new ImageIcon("C:\\Users\\Cliente\\Downloads\\imagemProjeto\\icomp.png"));
		
		JLabel labelTitulo = new JLabel("Sistema de Gerenciamentos de Documentos");
		labelTitulo.setFont(new Font(labelTitulo.getFont() .getName(), Font.PLAIN, 18));
		
		labelUsuario = new JLabel("Usuario", JLabel.LEFT);
		campoUsuario = new JTextField();
		labelSenha = new JLabel("Senha", JLabel.LEFT);
		campoSenha = new JPasswordField();
		botaoEntrar = new JButton("Entrar");
			
		labelTitulo.setBounds(50, 40, 850,40);
		labelUsuario.setBounds(200,190,200,40);
		campoUsuario.setBounds(200,220,200,40);
		labelSenha.setBounds(200,268,200,40);
		campoSenha.setBounds(200,300,200,40);
		botaoEntrar.setBounds(200,350,200,40);
				
		add(labelTitulo);
		add(labelUsuario);
		add(campoUsuario);
		add(labelSenha);
		add(campoSenha);
		add(botaoEntrar);
		
		setVisible(true);
			
		
	}
	
	private void criarEventos() {
		botaoEntrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(campoUsuario.getText().equals("123") && new String(campoSenha.getPassword()).equals("123")) {
					Navegador.inicio();
				}else {
					JOptionPane.showMessageDialog(null, "Acesso Negado");
				}
				
			}
			
		});
	}
	
	private void carregarImagemFundo() {
		try {
			imagemFundo = ImageIO.read(new File("C:\\Users\\Cliente\\Downloads\\imagemProjeto\\icomp02.png")); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (imagemFundo != null) {
			g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), this);
		}
	}
	
}
