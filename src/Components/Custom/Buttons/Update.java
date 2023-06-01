package Components.Custom.Buttons;

import Components.Custom.Panels.InformationPanel;
import Components.Custom.Tables.customTable;
import Util.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Update extends JButton implements ActionListener {

    private final customTable table = customTable.getInstance();


    public Update(){
        this.setText("Update");
        this.addActionListener(this);
        this.setIcon(Icons.UPDATE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(table.getSelectedRow() >= 0){
            InformationPanel.getInstance().updateEvent();
        }else{
            JOptionPane.showMessageDialog(null, "No row selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}