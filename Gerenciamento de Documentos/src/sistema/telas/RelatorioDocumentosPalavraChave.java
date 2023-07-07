package sistema.telas;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import sistema.navegacao.BancoDeDados;
import sistema.navegacao.Navegador;


/**
 * A classe RelatorioCargos representa a tela de exibição do relatório de documentos por palavra chave.
 */ 

public class RelatorioDocumentosPalavraChave extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
    private JLabel labelTitulo, labelDescricao;
    
    /**
     * Cria uma nova instância da classe RelatorioDocumentosPalavraChave.
     */
    

    public RelatorioDocumentosPalavraChave() {
        criarComponentes();
        Navegador.habilitarMenu();
    }

    private void criarComponentes() {
        setLayout(null);
        labelTitulo = new JLabel("Relatórios", JLabel.CENTER);
        labelTitulo.setFont(new Font(labelTitulo.getFont().getName(), Font.PLAIN, 20));
        labelDescricao = new JLabel("Relatório de Documentos por Referência", JLabel.CENTER);

        Object[][] dadosTabela = criarDadosTabela();

        String[] colunas = {"Data de arquivamento", "Interessado", "Tipo", "Descrição resumida", "Armazenamento", "Local de armazenamento"};

        JTable tabela = new JTable(dadosTabela, colunas);
        JScrollPane scrollPane = new JScrollPane(tabela);

        labelTitulo.setBounds(20, 20, 660, 40);
        labelDescricao.setBounds(20, 50, 660, 40);
        scrollPane.setBounds(60, 100, 1000, 500);

        add(labelTitulo);
        add(labelDescricao);
        add(scrollPane);
        setVisible(true);
    }

    private Object[][] criarDadosTabela() {
        Connection conexao;
        Statement instrucaoSQL;
        ResultSet resultados;

        try {
            String referencia = JOptionPane.showInputDialog(this, "Digite a referência do documento:");
            conexao = DriverManager.getConnection(BancoDeDados.stringDeConexao, BancoDeDados.usuario, BancoDeDados.senha);
            instrucaoSQL = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String query = "SELECT data_arquivamento, interessado_id, tipo, descricao, armazenamento, localizacao FROM documentos WHERE referencia = '" + referencia + "'";


            resultados = instrucaoSQL.executeQuery(query);
            resultados.last();
            int numLinhas = resultados.getRow();
            resultados.beforeFirst();

            Object[][] dados = new Object[numLinhas][6];
            int linha = 0;
            
            while (resultados.next()) {
                dados[linha][0] = resultados.getDate("data_arquivamento");
                dados[linha][1] = resultados.getString("interessado_id");
                dados[linha][2] = resultados.getString("tipo");
                dados[linha][3] = resultados.getString("descricao");
                dados[linha][4] = resultados.getString("armazenamento");
                dados[linha][5] = resultados.getString("localizacao");
                linha++;
            }

            return dados;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao criar o relatório.\n\n" + ex.getMessage());
            Navegador.inicio();
        }

        return null;
    }
}
