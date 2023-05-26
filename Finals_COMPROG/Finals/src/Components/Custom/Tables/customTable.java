package Components.Custom.Tables;

import Components.Custom.Models.customTableModel;

import javax.swing.*;
import javax.swing.table.TableRowSorter;

public class customTable extends JTable {

    customTableModel model = new customTableModel(this);

    public customTable(){
        this.setModel(model);
        this.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public customTableModel getModel() {
        return model;
    }

}
