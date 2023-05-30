package Components.Custom.Panels;

import Components.Custom.Tables.customTable;
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class InformationPanel extends JPanel {

    private final customTable table = customTable.getInstance();
    private static InformationPanel instance;
    final JLabel idLabel = new JLabel("ID: ");

    final JTextField nameField = new JTextField(10);
    final JTextField quantityField = new JTextField(10);
    final JTextField priceField = new JTextField(10);

    public static InformationPanel getInstance(){
        if(instance == null){
            instance = new InformationPanel();
        }
        return instance;
    }

    private InformationPanel(){
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel nameLabel = new JLabel("Name :");
        JLabel quantityLabel = new JLabel("Quantity :");
        JLabel priceLabel = new JLabel("Price :");

        this.setLayout(new GridBagLayout());
        this.setSize(new Dimension(200,200));

        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridy = 0;
        this.add(idLabel, constraints);
        constraints.gridy = 1;
        this.add(nameLabel, constraints);
        constraints.gridy = 2;
        this.add(nameField, constraints);
        constraints.gridy = 3;
        this.add(quantityLabel, constraints);
        constraints.gridy = 4;
        this.add(quantityField, constraints);
        constraints.gridy = 5;
        this.add(priceLabel, constraints);
        constraints.gridy = 6;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.add(priceField, constraints);
    }

    public void updateEvent(){
        Vector<Object> tmp = new Vector<>();
        Object id = table.getModel().getValueAt(table.getSelectedRow(), 0);
        Object a = nameField.getText().trim();
        Object b = quantityField.getText().trim();
        Object c = priceField.getText().trim();
        tmp.add(id);
        tmp.add(a);
        tmp.add(b);
        tmp.add(c);
        try{
            try(PreparedStatement pt = table.getModel().getConnector().getConnection().prepareStatement("update test set `Product Name`=?, Quantity=?, Price=? where ID=? ")){
                pt.setObject(1, a);
                pt.setObject(2, b);
                pt.setObject(3, c);
                pt.setObject(4, id);
                pt.executeUpdate();
                table.getModel().getRows().set(table.getSelectedRow(), tmp);
                table.getModel().fireTableDataChanged();
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public void deleteEvent(){
        try{
            try(PreparedStatement pt = table.getModel().getConnector()
                    .getConnection().prepareStatement("delete from test where ID=?")){
                pt.setObject(1, table.getValueAt(table.getSelectedRow(), 0));
                if(pt.executeUpdate() == 1){
                    table.getModel().getRows().remove(
                            table.getModel().getRows().get(
                                    table.getRowSorter().convertRowIndexToModel(table.getSelectedRow())));
                    table.getModel().fireTableDataChanged();
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void setFields(int row){
        idLabel.setText("ID: " + table.getValueAt(row, 0));
        nameField.setText(String.valueOf(table.getValueAt(row, 1)));
        quantityField.setText(String.valueOf(table.getValueAt(row, 2)));
        priceField.setText(String.valueOf(table.getValueAt(row, 3)));
    }
}
