package Controller;

import Interfaces.IConnector;

import java.sql.*;

public class ConnectionController implements IConnector {
    @Override
    public Connection getConnection() {
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/testbench","root","root");
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public static int getLatestIDCOUNT(){
        try{
            IConnector connector = new ConnectionController();
            try(Statement st = connector.getConnection().createStatement()){
                ResultSet rs = st.executeQuery("select max(ID) from test");
                if(rs.next()){
                    return rs.getInt(1) + 1;
                }
            }
            return 0;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
