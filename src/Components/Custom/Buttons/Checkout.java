package Components.Custom.Buttons;

import Components.Custom.Panels.InformationPanel;
import Util.Icons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checkout extends JButton implements ActionListener {

    public Checkout(){
        this.setText("Checkout");
        this.addActionListener(this);
        this.setIcon(Icons.RIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InformationPanel.getInstance().checkOutEvent();
    }
}
