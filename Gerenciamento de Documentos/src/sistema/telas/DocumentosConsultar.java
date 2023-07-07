package sistema.telas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import entidade.Documentos;
import sistema.navegacao.BancoDeDados;
import sistema.navegacao.Navegador;

/**
 * Classe que representa a tela de consulta de documentos.
 */

public class DocumentosConsultar extends JPanel {
	
	private static final long serialVersionUID = 1L;

    Documentos documentoAtual;
    JLabel labelTitulo, labelDocumento;
    JTextField campoDocumento;
    JButton botaoPesquisar, botaoEditar, botaoExcluir;
    DefaultListModel<Documentos> listaDocumentosModelo = new DefaultListModel<>();
    JList<Documentos> listaDocumentos;
    
    /**
     * Cria uma instância da tela de consulta de documentos.
     */

    public DocumentosConsultar() {
        criarComponentes();
        criarEventos();
        Navegador.habilitarMenu();
    }

    private void criarComponentes() {
        setLayout(null);

        labelTitulo = new JLabel("Consulta de Documentos", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelDocumento = new JLabel("Referência do documento", JLabel.LEFT);
        campoDocumento = new JTextField();
        botaoPesquisar = new JButton("Pesquisar Documento");
        botaoEditar = new JButton("Editar Documento");
        botaoEditar.setEnabled(false);
        botaoExcluir = new JButton("Excluir Documento");
        botaoExcluir.setEnabled(false);
        listaDocumentosModelo = new DefaultListModel<>();
        listaDocumentos = new JList<>(listaDocumentosModelo);
        listaDocumentos.setModel(listaDocumentosModelo);
        listaDocumentos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        labelTitulo.setBounds(20, 20, 660, 40);
        labelDocumento.setBounds(150, 120, 400, 20);
        campoDocumento.setBounds(150, 140, 400, 40);
        botaoPesquisar.setBounds(500, 140, 200, 40);
        listaDocumentos.setBounds(150, 200, 400, 240);
        botaoEditar.setBounds(560, 360, 150, 40);
        botaoExcluir.setBounds(560, 400, 150, 40);

        add(labelTitulo);
        add(labelDocumento);
        add(campoDocumento);
        add(listaDocumentos);
        add(botaoPesquisar);
        add(botaoEditar);
        add(botaoExcluir);

        setVisible(true);
    }
    
   
    private void criarEventos() {
        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlPesquisarDocumentos(campoDocumento.getText());
            }
        });
        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (documentoAtual != null) {
                    Navegador.documentosEditar(documentoAtual);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um documento para editar.");
                }
            }
        });
        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sqlDeletarDocumento();
            }
        });
        listaDocumentos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                documentoAtual = listaDocumentos.getSelectedValue();
                if (documentoAtual == null) {
                    botaoEditar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                } else {
                    botaoEditar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                }
            }
        });
    }

    private void sqlPesquisarDocumentos(String referencia) {
        
        Connection conexao;
        Statement instrucaoSQL;
        ResultSet resultados;

        try {
            
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultados = instrucaoSQL.executeQuery("SELECT * FROM documentos WHERE referencia like '%" + referencia + "%' ORDER BY referencia ASC");

            listaDocumentosModelo.clear();

            while (resultados.next()) {
                Documentos documento = new Documentos();
                documento.setId(resultados.getInt("id"));
                documento.setReferencia(resultados.getString("referencia"));
                documento.setDataArquivamento(resultados.getDate("data_arquivamento"));
  
                listaDocumentosModelo.addElement(documento);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar documentos.");
            Logger.getLogger(DocumentosInserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sqlDeletarDocumento() {
        String pergunta = "Deseja realmente excluir o Documento " + documentoAtual.getReferencia() + "?";
        int confirmacao = JOptionPane.showConfirmDialog(null, pergunta, "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
           
            Connection conexao;    
            Statement instrucaoSQL;
           

            try {
              
                conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);

                instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                instrucaoSQL.executeUpdate("DELETE FROM documentos WHERE id=" + documentoAtual.getId());

                JOptionPane.showMessageDialog(null, "Documento deletado com sucesso!");
                Navegador.inicio();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir Documento.");
                Logger.getLogger(DocumentosInserir.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
