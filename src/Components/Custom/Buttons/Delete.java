package Components.Custom.Buttons;

import Components.Custom.Panels.InformationPanel;
import Components.Custom.Tables.customTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete extends JButton implements ActionListener {
    private final customTable table = customTable.getInstance();


    public Delete(){
        this.setText("Delete");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(table.getSelectedRow() >= 0){
            InformationPanel.getInstance().deleteEvent();
            InformationPanel.getInstance().nullifyOnDelete();
        }else{
            JOptionPane.showMessageDialog(null, "No row selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}