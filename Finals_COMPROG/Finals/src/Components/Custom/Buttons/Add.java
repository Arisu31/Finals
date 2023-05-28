package Components.Custom.Buttons;

import Components.Custom.Panels.AddPanel;
import Components.Custom.Tables.customTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add extends JButton implements ActionListener {

    customTable table;

    public Add(customTable table){
        this.table = table;
        this.setText("Add");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AddPanel panel = new AddPanel(table);
        panel.init();
    }
}
