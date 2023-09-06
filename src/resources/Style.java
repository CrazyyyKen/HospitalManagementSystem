package resources;

public class Style {
	// Button Style
	private static final String IDLE_BUTTON_STYLE = "-fx-border-color: #ffffff; " + "-fx-background-color: #564A50; "
			+ "-fx-text-fill: #ffffff; " + "-fx-font-size: 20px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-border-width: 2px; " + "-fx-padding: 10 30 10 30; "
			+ "-fx-border-radius: 30px;" + "-fx-background-radius: 30px;";

	private static final String HOVERED_BUTTON_STYLE = "-fx-border-color: #564A50; " + "-fx-background-color: #ffffff; "
			+ "-fx-text-fill: #564A50; " + "-fx-font-size: 20px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-border-width: 2px; " + "-fx-padding: 10 30 10 30; "
			+ "-fx-border-radius: 30px;" + "-fx-background-radius: 30px;";
	private static final String ICON_BUTTON_STYLE = "-fx-border-color: #ffffff; " + "-fx-background-color: #564A50; "
			+ "-fx-text-fill: #ffffff; " + "-fx-font-size: 20px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-border-width: 2px; " + "-fx-padding: 10 30 10 20; "
			+ "-fx-border-radius: 40px;" + "-fx-background-radius: 40px;";
	private static final String HOVERED_ICON_BUTTON_STYLE = "-fx-border-color: #564A50; "
			+ "-fx-background-color: #ffffff; " + "-fx-text-fill: #564A50; " + "-fx-font-size: 20px; "
			+ "-fx-font-family: 'Courier'; " + "-fx-font-weight: bold;" + "-fx-border-width: 2px; "
			+ "-fx-padding: 10 30 10 20; " + "-fx-border-radius: 40px;" + "-fx-background-radius: 40px;";
	
	// Icon style
	private static final String ICON_STYLE = "-fx-border-color: #ffffff; " + "-fx-background-color: transparent; "
			+ "-fx-text-fill: #564A50; " + "-fx-font-size: 20px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-padding: 10 10 10 10; " + "-fx-border-width: 0px; ";
	private static final String HOVERED_ICON_STYLE = "-fx-border-color: #564A50; " + "-fx-background-color: #ffffff; "
			+ "-fx-text-fill: #564A50; " + "-fx-font-size: 20px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-border-width: 2px; " + "-fx-padding: 10 10 10 10; "
			+ "-fx-border-radius: 30px;" + "-fx-background-radius: 30px;";

	// Combobox style
	private static final String COMBOBOX_STYLE = "-fx-border-color: #564A50; " + "-fx-background-color: #FAF8F8; "
			+ "-fx-text-fill: #564A50; " + "-fx-font-size: 18px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-border-width: 2px; " + "-fx-padding: 5 8 5 8; "
			+ "-fx-border-radius: 20px;" + "-fx-background-radius: 20px;";
	
	private static final String COMBOBOX2_STYLE = "-fx-border-color: #D9D6D2; " + "-fx-background-color: #FAF8F8; "
			+ "-fx-text-fill: #564A50; " + "-fx-font-size: 18px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-border-width: 2px; " + "-fx-padding: 2 5 2 5; "
			+ "-fx-border-radius: 20px;" + "-fx-background-radius: 20px;";

	// Constant Text Style
	private static final String TEXT_STYLE = "-fx-text-fill: #564A50; " + "-fx-font-size: 16px; "
			+ "-fx-font-family: 'Courier'; ";
	
	// Constant Header Style
	private static final String HEADER_STYLE = "-fx-background-color: #98C3C3; -fx-border-width: 1px; -fx-border-color: #ffffff;";
	
	// Constant Text Field Style
	private static final String TEXTFIELD_STYLE = "-fx-text-fill: #000000; " + "-fx-font-size: 16px; "
			+ "-fx-font-family: 'Courier'; ";

	// Getters
	public static String getIdleButtonStyle() {
		return IDLE_BUTTON_STYLE;
	}

	public static String getHoveredButtonStyle() {
		return HOVERED_BUTTON_STYLE;
	}

	public static String getIconButtonStyle() {
		return ICON_BUTTON_STYLE;
	}

	public static String getHoveredIconButtonStyle() {
		return HOVERED_ICON_BUTTON_STYLE;
	}

	public static String getIconStyle() {
		return ICON_STYLE;
	}

	public static String getHoveredIconStyle() {
		return HOVERED_ICON_STYLE;
	}

	public static String getTextStyle() {
		return TEXT_STYLE;
	}

	public static String getTextfieldStyle() {
		return TEXTFIELD_STYLE;
	}

	public static String getHEADERStyle() {
		return HEADER_STYLE;
	}
	
	public static String getComboBoxStyle() {
		return COMBOBOX_STYLE;
	}
	
	public static String getComboBox2Style() {
		return COMBOBOX2_STYLE;
	}
}
