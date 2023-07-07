package conectividade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import sistema.navegacao.Navegador;

/**
 * Classe principal que inicia a aplicação e cria a janela principal.
 */

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                criarEExibirJanelaPrincipal();
            }
        });
    }
    
    /**
     * Cria e exibe a janela principal da aplicação.
     */

    private static void criarEExibirJanelaPrincipal() {
        if (sistema.navegacao.Sistema.frame == null) {
            sistema.navegacao.Sistema.frame = new JFrame();
        }
        
        JFrame janela = new JFrame("Funcionários Icomp");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        janela.setSize(800, 600);
        janela.setLocationRelativeTo(null); 

       
        Navegador.setJanelaPrincipal(janela);

        
        Connection conexao = null;
        try {
            String url = "jdbc:mysql://localhost:3306/meu_banco_de_dados?allowPublicKeyRetrieval=true&useSSL=false";
    		String usuario = "";
    		String senha = "";
            conexao = DriverManager.getConnection(url, usuario, senha);

            
            Navegador.setConexaoBanco(conexao);

           
            Navegador.login();

         
            janela.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
