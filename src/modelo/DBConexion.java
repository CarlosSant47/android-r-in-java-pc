package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
    private final String userDatabase = "root";
    private final String password = "";
    private final String dataBase = "extempo";
    private final String host = "localhost";


    public Connection openConection() throws SQLException {
        Connection con;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection("jdbc:mysql://" + host + "/" + dataBase, userDatabase, password);
        } catch (SQLException e) {
            System.out.println("Error de Conexion" + e.toString());
            con = null;
        }
        return con;
    }


    public void closeConection(Connection c) throws SQLException{
        try{
            if(!c.isClosed()){
                c.close();
            }
        }catch(SQLException e){
            System.out.println("Error al cerrar la conexión");
        }
    }
}
