import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class PrintJDBCDrivers {
    public static void main(String[] args) {
        for (Enumeration<Driver> e = DriverManager.getDrivers(); e.hasMoreElements(); ) {
            Driver driver = e.nextElement();
            print(driver);
        }
    }
    public static  void print(Driver driver){
        String className = driver.getClass().getName();
        int version = driver.getMajorVersion();
        System.out.println("Nome do Drive: " + className);
        System.out.println("Versão do Drive: " + version);
    }
}
