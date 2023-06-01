package Components.Custom.Panels;

import Components.Custom.Buttons.Checkout;
import Components.Custom.Tables.customTable;
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class InformationPanel extends JPanel {

    private final customTable table = customTable.getInstance();
    private static InformationPanel instance;
    final JLabel idLabel = new JLabel("ID: ");
    final JTextField nameField = new JTextField(10);
    final JTextField quantityField = new JTextField(10);
    final JTextField priceField = new JTextField(10);
    final JTextField totalField = new JTextField(10);
    final Font boldy = new Font("Segoe UI",Font.BOLD, 16);

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
        JLabel totalLabel = new JLabel("Total :");
        Insets commonField = new Insets(5,25,5,50);

        this.setLayout(new GridBagLayout());
        this.setSize(new Dimension(450,500));
        this.setBackground(Color.BLUE);

        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridy = 0;
        constraints.insets = new Insets(5,0,5,115);
        idLabel.setFont(boldy);
        this.add(idLabel, constraints);
        constraints.gridy = 1;
        constraints.insets = new Insets(5,0,5,90);
        nameLabel.setFont(boldy);
        this.add(nameLabel, constraints);
        constraints.gridy = 2;
        constraints.insets = commonField;
        this.add(nameField, constraints);
        constraints.gridy = 3;
        constraints.insets = new Insets(5,0,5,70);
        quantityLabel.setFont(boldy);
        this.add(quantityLabel, constraints);
        constraints.gridy = 4;
        constraints.insets = commonField;
        this.add(quantityField, constraints);
        constraints.gridy = 5;
        constraints.insets = new Insets(5,0,5,95);
        priceLabel.setFont(boldy);
        this.add(priceLabel, constraints);
        constraints.gridy = 6;
        constraints.insets = commonField;
        this.add(priceField, constraints);
        constraints.gridy = 7;
        constraints.insets = new Insets(100,-100,5,0);
        totalLabel.setFont(boldy);
        this.add(totalLabel, constraints);
        constraints.gridy = 7;
        constraints.insets = new Insets(140,-220,5,0);
        totalField.setText(String.valueOf(getTotal()));
        this.add(totalField, constraints);
        constraints.gridy = 7;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(180,-260,5,0);
        this.add(new Checkout(), constraints);
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
                updateTotalEvent();
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
                    updateTotalEvent();
                    table.getModel().fireTableDataChanged();
                }
            }
        }catch (SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void checkOutEvent(){
        try{
            try(PreparedStatement pt =table.getModel().getConnector().getConnection().prepareStatement("delete from test")){
                pt.executeUpdate();
                table.getModel().getRows().removeAllElements();
                updateTotalEvent();
                table.getModel().fireTableDataChanged();
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void setFields(int row){
        idLabel.setText("ID: " + table.getValueAt(row, 0));
        nameField.setText(String.valueOf(table.getValueAt(row, 1)));
        quantityField.setText(String.valueOf(table.getValueAt(row, 2)));
        priceField.setText(String.valueOf(table.getValueAt(row, 3)));
    }

    public void nullifyOnDelete(){
        idLabel.setText("ID: ");
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    public float getTotal(){
        float a;
        float total = 0;
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            float[] tmp = new float[2];
            tmp[0] = Float.parseFloat(String.valueOf(table.getValueAt(i, 2)));
            tmp[1] = Float.parseFloat(String.valueOf(table.getValueAt(i, 3)));
            a = tmp[0] * tmp[1];
            total += a;
        }
        return total;
    }

    public void updateTotalEvent(){
        totalField.setText(String.valueOf(getTotal()));
    }
}
