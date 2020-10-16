package ModelDAO.Model;

import java.sql.*;

/**
 *
 * @author GiTech
 */
public class ConnexionBD {

    private static Connection con;
    private static String url = "jdbc:sqlite:bdEcole.db";

    public static Connection getInstance() {
        if (con == null) {
        	System.out.println("connexion...");
            try {
            	Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection(url);

            } catch (SQLException e) {
                System.out.println("Erreur lors de la connexion a la BD. Code erreur : " + e.getErrorCode());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }

        return con;
    }

    public void Close() throws SQLException {
        con.close();
    }
    
    /*public static void main(String[] args) {
        Connection c = ConnexionBD.getInstance();
        
        System.out.println("c");
    }*/
}
