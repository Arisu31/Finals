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

    customTable table;
    JTextField nameField = new JTextField(10);
    JTextField occupationField = new JTextField(10);

    public AddPanel(customTable table){
        this.table = table;
    }

    public void init(){
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel idLabel = new JLabel("ID: ");
        JLabel count = new JLabel(String.valueOf(ConnectionController.getLatestIDCOUNT()));
        JLabel nameLabel = new JLabel("Name: ");
        JLabel occupationLabel = new JLabel("Occupation: ");
        JButton add = new JButton("Add");
        Insets commonInset = new Insets(10,15,10,15);

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(300,200));
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
        this.add(occupationLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        this.add(occupationField, constraints);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(0,0,0,0);
        this.add(add, constraints);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Vector<Object> tmp = new Vector<>();
        Object a = ConnectionController.getLatestIDCOUNT();
        Object b = nameField.getText().trim();
        Object c = occupationField.getText().trim();
        tmp.add(a);
        tmp.add(b);
        tmp.add(c);
        try{
            try(PreparedStatement pt = table.getModel().getConnector().getConnection().prepareStatement("insert into test (ID, Name, Occupation) values (?,?,?)")){
                pt.setObject(1, a);
                pt.setObject(2, b);
                pt.setObject(3, c);
                pt.executeUpdate();
                table.getModel().getRows().add(tmp);
                this.dispose();
            }
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
