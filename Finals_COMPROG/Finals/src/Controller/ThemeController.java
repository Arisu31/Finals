package Controller;

import com.formdev.flatlaf.FlatLaf;

public class ThemeController {

    public static void loadThemes(){
        FlatLaf.registerCustomDefaultsSource("Components.Themes.Properties");
    }
}
