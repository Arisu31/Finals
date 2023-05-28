package Components.Custom.Buttons;

import Components.Custom.Tables.customTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete extends JButton implements ActionListener {
    customTable table;

    public Delete(customTable table){
        this.setText("Delete");
        this.addActionListener(this);
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            try(PreparedStatement pt = table.getModel().getConnector()
                    .getConnection().prepareStatement("delete from test where ID=?")){
                pt.setObject(1, table.getValueAt(table.getSelectedRow(), 0));
                if(pt.executeUpdate() == 1){
                    table.getModel().getIndexOnAction(table.getSelectedRow());
                    table.getModel().fireTableDataChanged();
                    table.getSearch().search();
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
}
