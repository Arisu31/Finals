package Components;

import Components.Custom.Buttons.Delete;
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
        Delete delete = new Delete(table);

        this.setLayout(new GridBagLayout());
        this.setTitle("Test table");
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill =  GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(search, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        scrollPane.setFocusable(false);
        table.setFocusable(false);
        this.add(scrollPane, constraints);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(5,5,5,5);
        this.add(delete, constraints);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(Box.createRigidArea(new Dimension(0,0)), constraints);

        this.setVisible(true);
    }
}
