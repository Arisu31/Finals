package Util;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Icons {

    private static final String prefix = "/Util/Images/";

    private static URL getResource(String path) {
        return Icons.class.getResource(prefix.concat(path));
    }

    private static final Image search = new ImageIcon(getResource("search.png")).getImage();
    private static final Image add = new ImageIcon(getResource("add.png")).getImage();
    private static final Image update = new ImageIcon(getResource("Update.png")).getImage();
    private static final Image delete = new ImageIcon(getResource("delete.png")).getImage();
    private static final Image right = new ImageIcon(getResource("right_arrow.png")).getImage();
    public static final Image ICON = new ImageIcon(getResource("fav-ico.png")).getImage();
    public static final ImageIcon SEARCH = new ImageIcon(search.getScaledInstance(16,16,Image.SCALE_SMOOTH));
    public static final ImageIcon ADD = new ImageIcon(add.getScaledInstance(16,16,Image.SCALE_SMOOTH));
    public static final ImageIcon UPDATE = new ImageIcon(update.getScaledInstance(16,16,Image.SCALE_SMOOTH));
    public static final ImageIcon DELETE = new ImageIcon(delete.getScaledInstance(16,16,Image.SCALE_SMOOTH));
    public static final ImageIcon RIGHT = new ImageIcon(right.getScaledInstance(16,16,Image.SCALE_SMOOTH));

}
