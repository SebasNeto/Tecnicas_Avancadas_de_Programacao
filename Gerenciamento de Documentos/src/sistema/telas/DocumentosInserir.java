package sistema.telas;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


import entidade.Documentos;
import entidade.Funcionario;
import sistema.navegacao.BancoDeDados;
import sistema.navegacao.Navegador;

/**
 * Classe que representa a tela de inserção de documentos.
 */

public class DocumentosInserir extends JPanel {
	
	private static final long serialVersionUID = 1L;

    JLabel labelTitulo, labelReferencia, labelDataArquivamento, labelInteressado, labelTipo, labelDescricao, labelArmazenamento, labelLocalizacao;
    JTextField campoReferencia, campoTipo, campoDescricao, campoArmazenamento, campoLocalizacao;
    JFormattedTextField campoDataArquivamento;
    JComboBox<Funcionario> comboBoxInteressado;
    JButton botaoGravar;
    
    /**
     * Cria uma instância da tela de inserção de documentos.
     */

    public DocumentosInserir() {
        criarComponentes();
        criarEventos();
        Navegador.habilitarMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        
        labelDescricao = new JLabel("Descrição:", JLabel.LEFT);
        campoDescricao = new JTextField();

        labelTitulo = new JLabel("Cadastro de Documento", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelReferencia = new JLabel("Referência:", JLabel.LEFT);
        campoReferencia = new JTextField();
        labelDataArquivamento = new JLabel("Data de Arquivamento:", JLabel.LEFT);
        campoDataArquivamento = new JFormattedTextField();
        try {
        	@SuppressWarnings("unused")
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setValidCharacters("0123456789");
            dateMask.install(campoDataArquivamento);
        } catch (ParseException ex) {
            Logger.getLogger(DocumentosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelInteressado = new JLabel("Interessado:", JLabel.LEFT);
        comboBoxInteressado = new JComboBox<>();
        labelTipo = new JLabel("Tipo:", JLabel.LEFT);
        campoTipo = new JTextField();
        labelDescricao = new JLabel("Descrição:", JLabel.LEFT);
        campoDescricao = new JTextField();
        labelArmazenamento = new JLabel("Armazenamento:", JLabel.LEFT);
        campoArmazenamento = new JTextField();
        labelLocalizacao = new JLabel("Localização:", JLabel.LEFT);
        campoLocalizacao = new JTextField();
        botaoGravar = new JButton("Adicionar");
        


        labelTitulo.setBounds(20, 20, 660, 40);
        labelReferencia.setBounds(150, 80, 400, 20);
        campoReferencia.setBounds(150, 100, 400, 40);
        labelDataArquivamento.setBounds(150, 140, 400, 20);
        campoDataArquivamento.setBounds(150, 160, 400, 40);
        labelInteressado.setBounds(150, 200, 400, 20);
        
        labelDescricao.setBounds(150, 320, 400, 20);
        campoDescricao.setBounds(150, 340, 400, 40);
        
        comboBoxInteressado.setBounds(150, 220, 400, 40);
        labelTipo.setBounds(150,260, 400, 20);
        campoTipo.setBounds(150,280, 400, 40);


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
		
		
        sqlCarregarDocumentos();

        setVisible(true);
    }

    private void criarEventos() {
        botaoGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Documentos novoDocumento = new Documentos();
                novoDocumento.setReferencia(campoReferencia.getText());
                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataArquivamento = format.parse(campoDataArquivamento.getText());
                    novoDocumento.setDataArquivamento(dataArquivamento);
                } catch (ParseException ex) {
                    Logger.getLogger(DocumentosInserir.class.getName()).log(Level.SEVERE, null, ex);
                }
                Funcionario interessadoSelecionado = (Funcionario) comboBoxInteressado.getSelectedItem();
                if(interessadoSelecionado != null) 
                	novoDocumento.setInteressado(interessadoSelecionado);
                novoDocumento.setTipo(campoTipo.getText());
                novoDocumento.setDescricao(campoDescricao.getText());
                novoDocumento.setArmazenamento(campoArmazenamento.getText());
                novoDocumento.setLocalizacao(campoLocalizacao.getText());
                sqlInserirDocumento(novoDocumento);
            }
        });
    }

    private void sqlCarregarDocumentos() {

        Connection conexao;
        Statement instrucaoSQL;
        ResultSet resultados;

        try {
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * from funcionarios order by nome asc");
            comboBoxInteressado.removeAllItems();

            while (resultados.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultados.getInt("id"));
                funcionario.setNome(resultados.getString("nome"));
                funcionario.setSobrenome(resultados.getString("sobrenome"));
                comboBoxInteressado.addItem(funcionario);
            }
            comboBoxInteressado.updateUI();

            conexao.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar os funcionários");
            Logger.getLogger(DocumentosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlInserirDocumento(Documentos novoDocumento) {

        if(campoReferencia.getText().length() <= 2) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha a referência corretamente");
            return;
        }
        
        if(campoTipo.getText().length() <= 2) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha o tipo corretamente");
            return;
        }


        if(campoDataArquivamento.getText().length() <= 2) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha a data de arquivamento corretamente");
            return;
        }

        if(comboBoxInteressado.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione um interessado");
            return;
        }


        if(campoDescricao.getText().length() <= 2) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha a descrição corretamente");
            return;
        }


        if(campoArmazenamento.getText().length() <= 2) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha o armazenamento corretamente");
            return;
        }


        if(campoLocalizacao.getText().length() <= 2) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha a localização corretamente");
            return;
        }


        Connection conexao;
        PreparedStatement instrucaoSQL;

        try{
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            String template = "INSERT INTO documentos (referencia, data_arquivamento, interessado_id, tipo, descricao, armazenamento, localizacao) VALUES (?,?,?,?,?,?,?)";
            instrucaoSQL = conexao.prepareStatement(template);
            instrucaoSQL.setString(1, novoDocumento.getReferencia());
            
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Date dataArquivamento = formatoData.parse(campoDataArquivamento.getText());
            
            @SuppressWarnings("unused")
            java.sql.Date dataArquivamentoSQL = new java.sql.Date(dataArquivamento.getTime());
            
            instrucaoSQL.setDate(2, new java.sql.Date(novoDocumento.getDataArquivamento().getTime()));
            instrucaoSQL.setInt(3, novoDocumento.getInteressado().getId());
            instrucaoSQL.setString(4, novoDocumento.getTipo());
            instrucaoSQL.setString(5, novoDocumento.getDescricao());
            instrucaoSQL.setString(6, novoDocumento.getArmazenamento());
            instrucaoSQL.setString(7, novoDocumento.getLocalizacao());
            instrucaoSQL.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Documento adicionado com sucesso!");
            
            Navegador.inicio();
            conexao.close();

        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar o Documento.");
            Logger.getLogger(DocumentosInserir.class.getName()).log(Level.SEVERE, null, ex);

        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Por favor, insira uma data válida no formato dd/MM/yyyy.");
            Logger.getLogger(DocumentosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
