package Components.Custom.Panels;

import Components.Custom.Models.customTableModel;
import Components.Custom.Tables.customTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchPanel extends JPanel implements KeyListener {

    customTable table;
    JTextField search = new JTextField(20);
    TableRowSorter<customTableModel> sorter;

    public SearchPanel(customTable table){
        this.table = table;
        sorter = new TableRowSorter<>(table.getModel());
        init();
    }

    void init(){
        JLabel label = new JLabel("Search: ");

        this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
        search.addKeyListener(this);

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
        sorter.setRowFilter(RowFilter.regexFilter(search.getText().trim()));
        table.setRowSorter(sorter);
    }
}
