
package br.ufg.inf.especilizacao.data;

import br.ufg.inf.especializacao.model.Funcionario;
import br.ufg.inf.especializacao.model.Veiculo;
import br.ufg.inf.especializacao.model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VeiculoDao {

    public void incluirVeiculo(Veiculo veiculo) throws ClassNotFoundException, SQLException {
        
		Conexao conexao = new Conexao();
                Connection con = conexao.conectarNoBancoDeDados();
                /*conexão com as tabelas do banco e adcionando atributos na tabela */
                
		String sql = "INSERT INTO VEICULOS(ID, CHASSI, NAME, MARCA, MODELO, COR, ANO, VALOR)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, veiculo.getId_veiculo());
                ps.setString(2, veiculo.getChassi());
		ps.setString(3, veiculo.getNome());
                ps.setString(4, veiculo.getMarca());
                ps.setString(5, veiculo.getModelo());
                ps.setString(6, veiculo.getCor());
                ps.setString(7, veiculo.getAno());
                ps.setDouble(8, veiculo.getValor());
		ps.executeUpdate();
                ps.close();
		con.close();
                
    }
    public void incluirVendaVeiculo(Venda venda) throws ClassNotFoundException, SQLException{
        
        Conexao conexao = new Conexao();
                Connection con = conexao.conectarNoBancoDeDados();
        String sql = "INSERT INTO VENDA(ID_CLIENTE, CPF_FUNC, CHASSI, DATA_VENDA, QTDE, VALOR_UNITARIO, DESCONTO, VALORTOTAL)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
                ps.setLong(1, venda.getId_cliente());
                ps.setString(2, venda.getCpf_func());
                ps.setString(3, venda.getChassi());
	        ps.setDate(4,     venda.getDataVenda());
                ps.setInt(5,     venda.getQtde());
                ps.setFloat(6, venda.getValorUnitario());
                ps.setFloat(7, venda.getDesconto());
                ps.setFloat(8, venda.getVlrtotal());
		ps.executeUpdate();
                ps.close();
		con.close();
                
    } 
    
    
    public Veiculo getByName(String nome) throws SQLException {
        
        
        final String QUERY = "SELECT chassi,\n" +
                            "       cor,\n" +
                            "       ano,\n" +
                            "       valor,\n" +
                            "       id_veiculo\n" +
                            "  FROM veiculo_estoque;";
        Connection connection = Conection.getConnection();
        Statement comando = null;
        ResultSet resultado = null;
        try {
            comando = connection.createStatement();
            resultado = comando.executeQuery(QUERY);

            if (!resultado.next()) {
                throw new RuntimeException("Não foi encontrada a veiculo com o "
                        + " nome determinado");
            }

            Veiculo veiculo = new Veiculo();
            
            veiculo.setChassi(resultado.getCursorName(chassi));
            veiculo.setId_veiculo(resultado.getInt(4));
            veiculo.setNome(resultado.getString());
            return veiculo;
        } catch (SQLException e) {
            throw new RuntimeException("Pessoa não encontrada");
        } finally {
            if(comando != null){
                comando.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}
  
