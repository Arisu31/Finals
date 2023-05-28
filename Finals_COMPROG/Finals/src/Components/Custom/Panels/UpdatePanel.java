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

public class UpdatePanel extends JFrame implements ActionListener {

    int rowIndex;
    customTable table;
    JTextField nameField = new JTextField(10);
    JTextField occupationField = new JTextField(10);

    public UpdatePanel(customTable table, int row){
        this.table = table;
        rowIndex = row;
    }

    public void init(){
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel nameLabel = new JLabel("Name: ");
        JLabel occupationLabel = new JLabel("Occupation: ");
        JButton update = new JButton("Update");
        Insets commonInset = new Insets(10,15,10,15);

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(300,200));
        this.setTitle("Update product");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = commonInset;
        this.add(nameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        nameField.setText(String.valueOf(table.getModel().getValueAt(rowIndex, 1)));
        this.add(nameField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(occupationLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;
        occupationField.setText(String.valueOf(table.getModel().getValueAt(rowIndex, 2)));
        this.add(occupationField, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(0,0,0,0);
        update.addActionListener(this);
        this.add(update, constraints);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Vector<Object> tmp = new Vector<>();
        Object id = table.getModel().getValueAt(rowIndex, 0);
        Object a = nameField.getText().trim();
        Object b = occupationField.getText().trim();
        tmp.add(id);
        tmp.add(a);
        tmp.add(b);
        try{
            try(PreparedStatement pt = table.getModel().getConnector().getConnection().prepareStatement("update test set Name=?, Occupation=? where ID=? ")){
                pt.setObject(1, a);
                pt.setObject(2, b);
                pt.setObject(3, id);
                pt.executeUpdate();
                table.getModel().getRows().set(table.getSelectedRow(), tmp);
                table.getModel().fireTableDataChanged();
                this.dispose();
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
