import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO{
    Connection conexao = null;
    @Override
    public Connection connect(String urlConexao) {
        try {
            conexao = DriverManager.getConnection(urlConexao);
        } catch (SQLException e) {
            System.err.println("Não foi possível abrir a conexão com o banco!");
        }
        finally {
            try{
                if(conexao != null){
                    conexao.close();
                }
            }catch (SQLException exception){
                System.err.println(exception.getMessage());
            }
        }
        return conexao;
    }

    @Override
    public void createTable(String urlConexao) {

        StringBuffer query = new StringBuffer();
        query.append("CREATE TABLE IF NOT EXISTS cliente (");
        query.append("id integer PRIMARY KEY , ");
        query.append("nome text NOT NULL, ");
        query.append("idade integer, ");
        query.append("cpf text NOT NULL, ");
        query.append("rg text ");
        query.append(")");
        try {
            conexao = connect(urlConexao);
           PreparedStatement preparedStatement = conexao.prepareStatement(query.toString());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.println("Não foi possível criar o banco!");
        }
    }

    @Override
    public void insert(String urlConexao, Cliente cliente){
        String query = "INSERT INTO cliente VALUES (null, '" + cliente.getNome() + "', '" + cliente.getIdade() +"', '" + cliente.getCpf() + "', "
                + cliente.getRg() + ")";
        try {
            conexao = connect(urlConexao);
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Não foi possível inserir a pessoa no banco!");
        }
    }

    @Override
    public void selectAll(String urlConexao) {
        String query = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
      try {
          connect(urlConexao);
          PreparedStatement preparedStatement = conexao.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery();

          while(resultSet.next()){
              Cliente cliente = new Cliente();
              cliente.setId(resultSet.getInt("id"));
              cliente.setNome(resultSet.getString("nome"));
              cliente.setIdade(resultSet.getInt("idade"));
              cliente.setCpf(resultSet.getString("cpf"));
              cliente.setRg(resultSet.getString("rg"));

              clientes.add(cliente);
          }
          resultSet.close();
          preparedStatement.close();

          clientes.forEach(System.out::println);

      }catch (SQLException e){
          System.err.println("Não foi possível buscar os dados do banco!");
      }

    }

    @Override
    public void update(String urlConexao, int id, String name, Integer idade) {
        String query = "UPDATE cliente SET nome = '" + name + "', idade = '" + idade
                + " WHERE pessoa.id = " + id;
        try {
            conexao = connect(urlConexao);
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Não foi possível atualizar os dados da pessoa no banco!");
        }
    }

    @Override
    public void delete(String urlConexao, int id) {
        String query = "DELETE FROM cliente WHERE id = " + id;
        try {
            conexao = connect(urlConexao);
            PreparedStatement preparedStatement = conexao.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Não foi possível remover a pessoa no banco!");
        }
    }

}
