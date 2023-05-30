package Components.Custom.Buttons;

import Components.Custom.Panels.AddPanel;
import Components.Custom.Tables.customTable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Add extends JButton implements ActionListener {

    public Add(){
        this.setText("Add");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AddPanel panel = new AddPanel(customTable.getInstance());
        panel.init();
    }
}
