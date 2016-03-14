package Util;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;

public class ZResource {

    private static ResourceBundle resources;
    private static String imageSuffix = "Image";

    static {
        try {
            resources = ResourceBundle.getBundle("Util.resource.ZALPHA",
                    Locale.getDefault());
        } catch (MissingResourceException mre) {
            System.err.println("Util/resource/ZALPHA.properties not found");
            System.exit(1);
        }
    }

    public static String getAppHomePage() {
        return getResourceString("Application.homepage");
    }

    public static java.awt.Image getAppImage() {
        return getAppImageIcon().getImage();
    }

    public static ImageIcon getAppImageIcon() {
        return getImageIcon("Application");
    }

    public static String getAppName() {
        return getResourceString("Application.name");
    }

    public static String getAppTitle() {
        return getResourceString("Application.title");
    }

    public static String getAppVersion() {
        return getResourceString("Application.version");
    }

    public static java.awt.Image getImage(String cmd) {
        return getImageIcon(cmd).getImage();
    }

    public static ImageIcon getImageIcon(String cmd) {
        URL url = new ZResource().getResources(cmd + imageSuffix);
        if (url != null) {
            return new ImageIcon(url);
        }
        System.out.println("�ļ�" + cmd + "�����ڣ�");
        return null;
    }

    public static String getLabel(String s) {
        String str;
        try {
            str = resources.getString(s + "Label");
        } catch (MissingResourceException mre) {
            str = null;
        }
        if (str == null) {
            System.out.println("�ַ�" + s + "�����ڣ�");
        }
        return str;
    }

    public static String getResourceString(String nm) {
        String str;
        try {
            str = resources.getString(nm);
        } catch (MissingResourceException mre) {
            str = null;
        }
        return str;
    }

    public static ImageIcon getWallpaper() {
        return getImageIcon("wallpaper");
    }

    public static ImageIcon getWelcomeImage() {
        return getImageIcon("welcome");
    }

    public URL getResources(String key) {
        String name = getResourceString(key);
        if (name != null) {
            URL url = this.getClass().getResource(name);
            return url;
        }
        return null;
    }
}
