package Components.Custom.Buttons;

import Components.Custom.Panels.AddPanel;
import Components.Custom.Panels.UpdatePanel;
import Components.Custom.Tables.customTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update extends JButton implements ActionListener {

    customTable table;

    public Update(customTable table){
        this.table = table;
        this.setText("Update");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(table.getSelectedRow() >= 0){
            UpdatePanel panel = new UpdatePanel(table, table.getSelectedRow());
            panel.init();
        }else{
            JOptionPane.showMessageDialog(null, "No row selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
