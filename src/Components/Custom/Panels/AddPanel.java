package Components.Custom.Panels;

import Components.Custom.Tables.customTable;
import Controller.ConnectionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class AddPanel extends JFrame implements ActionListener {

    final customTable table;
    final JTextField nameField = new JTextField(10);
    final JTextField quantityField = new JTextField(10);
    final JTextField priceField = new JTextField(10);

    public AddPanel(customTable table){
        this.table = table;
    }

    public void init(){
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel idLabel = new JLabel("ID: ");
        JLabel count = new JLabel(String.valueOf(ConnectionController.getLatestIDCOUNT()));
        JLabel nameLabel = new JLabel("Name :");
        JLabel quantityLabel = new JLabel("Quantity :");
        JLabel priceLabel = new JLabel("Price :");
        JButton add = new JButton("Add");
        Insets commonInset = new Insets(10,15,10,15);

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(350,300));
        this.setTitle("Add product");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.insets = commonInset;
        add.addActionListener(this);
        this.add(idLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = commonInset;
        this.add(count, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = commonInset;
        this.add(nameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(nameField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(quantityLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(quantityField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(priceLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.add(priceField, constraints);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(0,0,0,0);
        this.add(add, constraints);

        this.setVisible(true);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Vector<Object> tmp = new Vector<>();
        Object a = ConnectionController.getLatestIDCOUNT();
        Object b = nameField.getText().trim();
        Object c = quantityField.getText().trim();
        Object d = priceField.getText().trim();
        tmp.add(a);
        tmp.add(b);
        tmp.add(c);
        tmp.add(d);
        try{
            try(PreparedStatement pt = table.getModel().getConnector().getConnection().prepareStatement("insert into test (ID, `Product Name`, Quantity, Price) values (?,?,?,?)")){
                pt.setObject(1, a);
                pt.setObject(2, b);
                pt.setObject(3, c);
                pt.setObject(4, d);
                pt.executeUpdate();
                table.getModel().getRows().add(tmp);
                table.getModel().fireTableDataChanged();
                InformationPanel.getInstance().updateTotalEvent();
                this.dispose();
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}