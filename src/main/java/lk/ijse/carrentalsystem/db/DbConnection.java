package lk.ijse.carrentalsystem.db;

import org.jetbrains.annotations.Contract;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental", "root", "1234");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnection getInstance() throws SQLException {
        if (dbConnection == null){
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public Connection getConnection(){

        return connection;
    }
}
