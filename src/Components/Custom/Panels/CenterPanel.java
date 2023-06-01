package Components.Custom.Panels;

import Components.Custom.Buttons.Add;
import Components.Custom.Buttons.Delete;
import Components.Custom.Buttons.Update;
import Components.Custom.Tables.customTable;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {


    private static CenterPanel instance;
    private static final customTable table = customTable.getInstance();

    public static CenterPanel getInstance(){
        if(instance == null){
            instance = new CenterPanel();
        }
        return instance;
    }

    private CenterPanel(){
        GridBagConstraints constraints = new GridBagConstraints();
        JScrollPane scrollPane = new JScrollPane(table);
        this.setLayout(new GridBagLayout());

        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill =  GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        scrollPane.setFocusable(false);
        table.setFocusable(false);
        constraints.insets = new Insets(5,15,5,15);
        this.add(scrollPane, constraints);
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5,15,5,5);
        this.add(new Add(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5,95,5,5);
        this.add(new Update(), constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(5,190,5,5);
        this.add(new Delete(), constraints);
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(Box.createRigidArea(new Dimension(0,0)), constraints);
    }
}
