package Controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class TableController{

    /**
     * Retrieves a collection of data from a {@code ResultSet} to store into a Vector.
     * @param rs ResultSet to be parsed
     * @return A Vector of strings that holds the name for each table header
     */
    public Vector<String> getColumnNames(ResultSet rs){
        try{
            ResultSetMetaData metaData = rs.getMetaData();

            Vector<String> Names = new Vector<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                Names.add(metaData.getColumnName(i));
            }
            return Names;
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    /**
     * Retrieves the data from a {@code ResultSet} to store into a Vector. <br>
     * However, this method call is resource intensive as it implements the {@code getObject()} method to parse through the data.
     * @param rs ResultSet to be parsed
     * @return A Vector of vectors that holds data for each specific cell
     */
    @SuppressWarnings("rawtypes")
    public Vector<Vector> getRowData(ResultSet rs){
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            Vector<Vector> dataVector = new Vector<>(); // Column
            while(rs.next()){
                Vector<Object> rowValue = new Vector<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    rowValue.add(rs.getObject(i));
                }
                dataVector.add(rowValue);
            }
            return dataVector;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}