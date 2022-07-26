import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ClienteDAOImpl implements ClienteDAO{
    private static Connection conexao;
    private static String urlConexao = "jdbc:sqlite:banco.sqlite";
    @Override
    public Connection connect(String urlConexao) {
        try {
            Class.forName("org.sqlite.JDBC");
            conexao = DriverManager.getConnection(urlConexao);
        } catch (SQLException e1) {
            System.err.println("Não foi possível abrir a conexão com o banco!");
        } catch (ClassNotFoundException e2) {
            System.err.println("Ocorreu uma falha ao utilizar o driver!");
        }
        return conexao;
    }

    @Override
    public void createTable(String urlConexao) {
        try {
            conexao = connect(urlConexao);
            Statement stm = conexao.createStatement();
            stm.executeUpdate("DROP TABLE IF EXISTS cliente");

            stm.executeUpdate("CREATE TABLE cliente (" + "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL," + "idade INTEGER NOT NULL" +"cpf TEXT NOT NULL," + "rg TEXT NOT NULL," );

                       stm.close();
        } catch (SQLException e) {
            System.err.println("Não foi possível criar o banco!");
        }
    }

    @Override
    public void insert(String urlConexao, Cliente cliente){
        try {
            String query = "INSERT INTO pessoa VALUES (null, '" + cliente.getNome() + "', '" + cliente.getIdade() +"', '" + cliente.getCpf() + "', "
                    + cliente.getRg() + ")";
            alterarBD(query, urlConexao);
        } catch (SQLException e) {
            System.err.println("Não foi possível inserir a pessoa no banco!");
        }
    }

    @Override
    public void selectAll(String urlConexao) {
      try {
          String query = "SELECT * FROM cliente";
          alterarBD(query, urlConexao);
      }catch (SQLException e){
          System.err.println("Não foi possível buscar os dados do banco!");
      }

    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        try {
            String query = "UPDATE cliente SET nome = '" + name + "', idade = '" + idade
                   + " WHERE pessoa.id = " + id;
            alterarBD(query, urlConexao);
        } catch (SQLException e) {
            System.err.println("Não foi possível atualizar os dados da pessoa no banco!");
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        try {
            String query = "DELETE FROM cliente WHERE id = " + id;
            alterarBD(query, urlConexao);
        } catch (SQLException e) {
            System.err.println("Não foi possível remover a pessoa no banco!");
        }
    }
    private void alterarBD(String query, String urlConexao)throws SQLException{
        Connection bd = connect(urlConexao);
        Statement stm = bd.createStatement();
        stm.executeUpdate(query);
        stm.close();
    }
}
