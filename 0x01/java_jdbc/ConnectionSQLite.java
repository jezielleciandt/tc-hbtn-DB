import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQLite {
    public static void main(String[] args) {
    initConnection();
    }
    public static void  initConnection(){
        Connection connection = null;
        try{
            String url = "jdbc:sqlite:sqlite_database_2022.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Conexão estabelecida com SQLite.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
