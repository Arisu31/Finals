package Components.Custom.Tables;

import Components.Custom.Models.customTableModel;
import Components.Custom.Panels.SearchPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

public class customTable extends JTable {

    customTableModel model = new customTableModel(this);
    SearchPanel search;

    public customTable(){
        this.setModel(model);
        this.setRowSorter(new TableRowSorter<>(model));
        this.setSelectionModel(new customSelectionModel(this));
        this.getTableHeader().setReorderingAllowed(false);
    }

    @Override
    public customTableModel getModel() {
        return model;
    }

    public void setSearch(SearchPanel search){
        this.search = search;
    }

    public SearchPanel getSearch(){
        return search;
    }

}

class customSelectionModel extends DefaultListSelectionModel{

    customTable table;

    public customSelectionModel(customTable table){
        this.table = table;
        this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
    }
}
