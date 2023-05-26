package Components;

import Components.Custom.Panels.SearchPanel;
import Components.Custom.Tables.customTable;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(){}

    public void init(){
        FlatLightLaf.setup();
        GridBagConstraints constraints = new GridBagConstraints();
        customTable table = new customTable();
        JScrollPane scrollPane = new JScrollPane(table);
        SearchPanel search = new SearchPanel(table);

        this.setLayout(new GridBagLayout());
        this.setTitle("Test table");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill =  GridBagConstraints.HORIZONTAL;
        this.add(search, constraints);
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(scrollPane, constraints);
        scrollPane.setFocusable(false);
        table.setFocusable(false);

        this.setVisible(true);
    }
}
