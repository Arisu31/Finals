package Components.Custom.Models;

import Components.Custom.Tables.customTable;
import Controller.ConnectionController;
import Controller.TableController;
import Interfaces.IConnector;

import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

public class customTableModel extends AbstractTableModel {

    TableController controller = new TableController();
    IConnector connector = new ConnectionController();
    customTable table;
    ResultSetMetaData metaData;
    ResultSet resultSet;
    Vector<String> columns;
    @SuppressWarnings("rawtypes")
    Vector<Vector> rows;

    public customTableModel(customTable table){
        try{
            try(PreparedStatement pt = connector.getConnection().prepareStatement("select * from test")){
                this.table = table;
                resultSet = pt.executeQuery();
                metaData = resultSet.getMetaData();
                columns = controller.getColumnNames(resultSet);
                rows = controller.getRowData(resultSet);
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public String getColumnName(int column) {
        return columns.elementAt(column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        Vector<Objects> temp = rows.elementAt(row);
        return temp.elementAt(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
