package arcgis10_2.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class Resources {

	static Locale englishLocale = new Locale("en", "US");
	public static ResourceBundle gdb = ResourceBundle.getBundle("resourceDDB");
	public static ResourceBundle messages = ResourceBundle.getBundle("resourceMessages", englishLocale);

}
