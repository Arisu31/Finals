package Components.Custom.Tables;

import Components.Custom.Models.customTableModel;
import Components.Custom.Panels.InformationPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

public class customTable extends JTable {

    final customTableModel model = new customTableModel(this);
    private static customTable instance;

    public static customTable getInstance(){
        if(instance == null){
            instance = new customTable();
        }
        return instance;
    }

    private customTable(){
        this.setModel(model);
        this.setRowSorter(new TableRowSorter<>(model));
        this.setSelectionModel(new customSelectionModel());
        this.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public customTableModel getModel() {
        return model;
    }

}

class customSelectionModel extends DefaultListSelectionModel implements ListSelectionListener {

    public customSelectionModel(){
        this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting() && customTable.getInstance().getSelectedRow() >= 0){
            InformationPanel.getInstance().setFields(customTable.getInstance().getSelectedRow());
        }
    }
}