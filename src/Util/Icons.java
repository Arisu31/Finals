package Util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Icons {

    private static final String prefix = "Util/Images/";

    private static URL getResource(String path) {
        return Icons.class.getResource(prefix.concat(path));
    }

    private static final Image search = new ImageIcon("../Resources/Images/search.png").getImage();
    private static final Image add = new ImageIcon("../Resources/Images/add.png").getImage();
    public static final ImageIcon SEARCH = new ImageIcon(search.getScaledInstance(16,16,Image.SCALE_SMOOTH));
    public static final ImageIcon ADD = new ImageIcon(add.getScaledInstance(32,32,Image.SCALE_SMOOTH));

}
