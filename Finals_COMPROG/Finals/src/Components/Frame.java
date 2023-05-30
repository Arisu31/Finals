package Components;

import Components.Custom.Panels.CenterPanel;
import Components.Custom.Panels.InformationPanel;
import Components.Custom.Panels.SearchPanel;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public void init(){
        FlatLightLaf.setup();
        CenterPanel centerPanel = CenterPanel.getInstance();
        SearchPanel search = new SearchPanel();
        InformationPanel informationPanel = InformationPanel.getInstance();

        this.setLayout(new BorderLayout());
        this.setTitle("Point of Sale");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(750,600));

        this.add(search, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(informationPanel, BorderLayout.EAST);

        this.setVisible(true);
    }
}
