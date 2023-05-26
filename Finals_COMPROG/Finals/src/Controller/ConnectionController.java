package Controller;

import Interfaces.IConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionController implements IConnector {
    @Override
    public Connection getConnection() {
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/testbench","root","root");
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
