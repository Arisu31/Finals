package Components.Custom.Panels;

import Components.Custom.Tables.customTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchPanel extends JPanel implements KeyListener {

    customTable table;
    JTextField search = new JTextField(20);

    public SearchPanel(customTable table){
        this.table = table;
        init();
    }

    void init(){
        JLabel label = new JLabel("Search: ");

        this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));

        this.add(label);
        this.add(search);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
