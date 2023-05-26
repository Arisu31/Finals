package Components.Custom.Tables;

import Components.Custom.Models.customTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class customTable extends JTable {

    customTableModel model = new customTableModel(this);
    TableRowSorter<customTableModel> sorter = new TableRowSorter<>();

    public customTable(){
        this.setModel(model);
//        this.setRowSorter(sorter);
    }

    @Override
    public customTableModel getModel() {
        return model;
    }
}
