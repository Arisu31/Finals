package Components.Custom.Models;

import Components.Custom.Tables.customTable;
import Controller.ConnectionController;
import Controller.TableController;
import Interfaces.IConnector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

public class customTableModel extends AbstractTableModel implements TableModelListener {

    int index;
    TableController controller = new TableController();
    IConnector connector = new ConnectionController();
    customTable table;
    ResultSetMetaData metaData;
    ResultSet resultSet;
    Vector<String> columns;
    @SuppressWarnings("rawtypes")
    Vector<Vector> rows;

    public customTableModel(customTable table){
        this.table = table;
        this.addTableModelListener(this);
        loadData();
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

    public IConnector getConnector() {
        return connector;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        System.out.println(rows.size() + " row size");
        switch (e.getType()){
            case 0 -> rows.remove(index);
            default -> System.out.println("NOMAS!");
        }
    }

    public void loadData(){
        try{
            try(PreparedStatement pt = connector.getConnection().prepareStatement("select * from test")){
                resultSet = pt.executeQuery();
                metaData = resultSet.getMetaData();
                columns = controller.getColumnNames(resultSet);
                rows = controller.getRowData(resultSet);
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void getIndexOnAction(int i){
        this.index = i;
    }
}
