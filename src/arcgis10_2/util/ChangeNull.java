package arcgis10_2.util;

public class ChangeNull {

	public static Double ToZero(Object value) {
		Double zero = 0.0;
		if (!(value == null || value.equals(null)
				|| value.equals("null") ||  value == "null"
				|| value == "" || value.equals("") || value == " " ))
			zero = (Double) value;
		return zero;
	}

	public static String ToEmpty(Object value) {
		String empty = "";
		if (!(value == null || value.equals(null)
				|| value.equals("null") ||  value == "null"
				|| value == "" || value.equals("") || value == " " ))
			empty = value.toString();
		return empty;
	}

}
