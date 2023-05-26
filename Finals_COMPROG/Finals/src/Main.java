import Components.Frame;
import Controller.ThemeController;

public class Main {

    public static void main(String[] args) {
        ThemeController.loadThemes();
        Frame a = new Frame();
        a.init();
    }

}
