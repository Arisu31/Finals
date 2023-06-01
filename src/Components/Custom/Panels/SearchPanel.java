package Components.Custom.Panels;

import Components.Custom.Models.customTableModel;
import Components.Custom.Tables.customTable;
import Util.Icons;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchPanel extends JPanel implements KeyListener {

    private final customTable table = customTable.getInstance();
    final JTextField search = new JTextField(20);
    final TableRowSorter<customTableModel> sorter;

    public SearchPanel(){
        sorter = new TableRowSorter<>(table.getModel());
        init();
    }

    void init(){
        JLabel label = new JLabel(Icons.SEARCH);

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
        search();
    }

    public void search(){
        sorter.setRowFilter(RowFilter.regexFilter(search.getText().trim()));
        table.setRowSorter(sorter);
    }
}
