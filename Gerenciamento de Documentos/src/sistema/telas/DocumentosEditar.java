package sistema.telas;

import java.util.Date;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidade.Documentos;
import entidade.Funcionario;
import sistema.navegacao.BancoDeDados;
import sistema.navegacao.Navegador;

/**
 * Classe que representa a tela de edição de documentos.
 */

public class DocumentosEditar extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	
	 Documentos documentoAtual;
	 JLabel labelTitulo, labelReferencia, labelDataArquivamento, labelInteressado, labelTipo, labelDescricao, labelArmazenamento, labelLocalizacao;
	 JTextField campoReferencia, campoDataArquivamento, campoTipo, campoDescricao, campoArmazenamento, campoLocalizacao;
	 JComboBox<Funcionario> comboBoxInteressado;;
	 JButton botaoGravar;
	 
		/**
		 * Cria uma instância da tela de edição de documentos.
		 * @param documento O documento a ser editado.
		 */
	  
	 public DocumentosEditar(Documentos documento){
		 documentoAtual = documento;
	     criarComponentes();
	     criarEventos();
	     Navegador.habilitarMenu();
	  }

	  private void criarComponentes() {
	     setLayout(null);
	     String textoLabel = "Editar Documento "+documentoAtual.getReferencia();
	     labelTitulo = new JLabel(textoLabel, JLabel.CENTER);
	     labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));      
	     labelReferencia = new JLabel("Referência:", JLabel.LEFT);
	     campoReferencia = new JTextField(documentoAtual.getReferencia());     
	     labelDataArquivamento = new JLabel("Data de Arquivamento:", JLabel.LEFT); 
	     campoDataArquivamento = new JTextField(); 
	     
	     if (documentoAtual.getDataArquivamento() != null) {
	         SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	         String dataFormatada = formato.format(documentoAtual.getDataArquivamento());
	         campoDataArquivamento.setText(dataFormatada);
	     }
	     
	     labelInteressado = new JLabel("Interessado:", JLabel.LEFT);
	     comboBoxInteressado = new JComboBox<>();
	     labelTipo = new JLabel("Tipo:", JLabel.LEFT);
	     campoTipo = new JTextField(documentoAtual.getTipo());
	     labelDescricao = new JLabel("Descrição:", JLabel.LEFT);
	     campoDescricao = new JTextField(documentoAtual.getDescricao());
	     labelArmazenamento = new JLabel("Armazenamento:", JLabel.LEFT);
	     campoArmazenamento = new JTextField(documentoAtual.getArmazenamento());
	     labelLocalizacao = new JLabel("Localização:", JLabel.LEFT);
	     campoLocalizacao = new JTextField(documentoAtual.getLocalizacao());
	     botaoGravar = new JButton("Salvar");
	     


	        labelTitulo.setBounds(20, 20, 660, 40);
	        labelReferencia.setBounds(150, 80, 400, 20);
	        campoReferencia.setBounds(150, 100, 400, 40);
	        labelDataArquivamento.setBounds(150, 140, 400, 20);
	        campoDataArquivamento.setBounds(150, 160, 400, 40);
	        labelInteressado.setBounds(150, 200, 400, 20);
	        comboBoxInteressado.setBounds(150, 220, 400, 40);
	        labelTipo.setBounds(150,260, 400, 20);
	        campoTipo.setBounds(150,280, 400, 40);
	        
	        labelDescricao.setBounds(150, 320, 400, 20);
	        campoDescricao.setBounds(150, 340, 400, 40);

	        labelLocalizacao.setBounds(150, 400, 400, 40);
	        campoLocalizacao.setBounds(150, 440, 400,30);
	        
	        
	        labelArmazenamento.setBounds(150, 480, 400, 40);
	        campoArmazenamento.setBounds(150, 520, 400, 40);
	        

	        
			botaoGravar.setBounds(560, 480, 130, 40);
			
			add(labelTitulo);
			add(labelReferencia);
			add(campoReferencia);
			add(labelDataArquivamento);
			add(campoDataArquivamento);
			add(labelInteressado);
			add(comboBoxInteressado);
			add(labelTipo);
			add(campoTipo);
			add(labelDescricao);
			add(campoDescricao);
			add(labelArmazenamento);
			add(campoArmazenamento);
			add(labelLocalizacao);
			add(campoLocalizacao);
			add(botaoGravar);
			
			sqlAtualizarDocumento();
			
			preencherComboBoxFuncionarios();

			setVisible(true);
	  }
	  
	  private void preencherComboBoxFuncionarios() {

		    Connection conexao;
		    Statement instrucaoSQL;
		    ResultSet resultados;

		    try {

		        conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);
		        instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        resultados = instrucaoSQL.executeQuery("SELECT * FROM funcionarios ORDER BY nome ASC");

		        while (resultados.next()) {
		            Funcionario funcionario = new Funcionario();
		            funcionario.setId(resultados.getInt("id"));
		            funcionario.setNome(resultados.getString("nome"));
		            funcionario.setSobrenome(resultados.getString("sobrenome"));
		            
		            comboBoxInteressado.addItem(funcionario);
		        }

		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar funcionários.");
		        Logger.getLogger(DocumentosEditar.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}


	  private void criarEventos() {
		    botaoGravar.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		    
		            documentoAtual.setReferencia(campoReferencia.getText());
		            
		            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		            try {
		                Date dataArquivamento = formatoData.parse(campoDataArquivamento.getText());
		                documentoAtual.setDataArquivamento(dataArquivamento);
		            } catch (ParseException ex) {
		                JOptionPane.showMessageDialog(null, "Por favor, insira uma data válida no formato dd/MM/yyyy.");
		                return;
		            }
		            
		            documentoAtual.setInteressado((Funcionario)comboBoxInteressado.getSelectedItem());
		            documentoAtual.setTipo(campoTipo.getText());
		            documentoAtual.setDescricao(campoDescricao.getText());
		            documentoAtual.setArmazenamento(campoArmazenamento.getText());
		            documentoAtual.setLocalizacao(campoLocalizacao.getText());

		            sqlAtualizarDocumento();                       
		        }
		    });
		}


	  private void sqlAtualizarDocumento() {

		    if(campoReferencia.getText().length() <= 2) {
		        JOptionPane.showMessageDialog(null, "Por favor, preencha a referência corretamente.");
		        return;
		    }

		    if(campoTipo.getText().length() <= 2) {
		        JOptionPane.showMessageDialog(null, "Por favor, preencha o tipo corretamente.");
		        return;
		    }

		    if(campoDescricao.getText().length() <= 10) {
		        JOptionPane.showMessageDialog(null, "Por favor, preencha a descrição corretamente.");
		        return;
		    }

		    if(campoArmazenamento.getText().length() <= 2) {
		        JOptionPane.showMessageDialog(null, "Por favor, preencha o armazenamento corretamente.");
		        return;
		    }

		    if(campoLocalizacao.getText().length() <= 2) {
		        JOptionPane.showMessageDialog(null, "Por favor, preencha a localização corretamente.");
		        return;
		    }


		    Connection conexao;
		    PreparedStatement instrucaoSQL;

		    try {

		        conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);
		        String template = "UPDATE documentos SET referencia=?, data_arquivamento=?, interessado_id=?, tipo=?, descricao=?, armazenamento=?, localizacao=? WHERE id=?";
		        java.sql.Date dataArquivamento = new java.sql.Date(documentoAtual.getDataArquivamento().getTime());
		        
		        instrucaoSQL = conexao.prepareStatement(template);
		    
		        instrucaoSQL.setString(1, documentoAtual.getReferencia());
		        instrucaoSQL.setDate(2, dataArquivamento);
		        instrucaoSQL.setInt(3, documentoAtual.getInteressado().getId()); 
		        instrucaoSQL.setString(4, documentoAtual.getTipo());
		        instrucaoSQL.setString(5, documentoAtual.getDescricao());
		        instrucaoSQL.setString(6, documentoAtual.getArmazenamento());
		        instrucaoSQL.setString(7, documentoAtual.getLocalizacao());
		        instrucaoSQL.setInt(8, documentoAtual.getId());
		        instrucaoSQL.executeUpdate();

		        JOptionPane.showMessageDialog(null, "Documento atualizado com sucesso!");
		        Navegador.inicio();

		        conexao.close();

		    } catch (SQLException ex) {
		        JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar o Documento.");
		        Logger.getLogger(DocumentosEditar.class.getName()).log(Level.SEVERE, null, ex);
		    }
		}
}
