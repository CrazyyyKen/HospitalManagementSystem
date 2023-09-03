package resources;

public class Style {
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
	private static final String ICON_STYLE = "-fx-border-color: #ffffff; " + "-fx-background-color: transparent; "
			+ "-fx-text-fill: #564A50; " + "-fx-font-size: 20px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-padding: 10 10 10 10; " + "-fx-border-width: 0px; ";
	private static final String HOVERED_ICON_STYLE = "-fx-border-color: #564A50; " + "-fx-background-color: #ffffff; "
			+ "-fx-text-fill: #564A50; " + "-fx-font-size: 20px; " + "-fx-font-family: 'Courier'; "
			+ "-fx-font-weight: bold;" + "-fx-border-width: 2px; " + "-fx-padding: 10 10 10 10; "
			+ "-fx-border-radius: 30px;" + "-fx-background-radius: 30px;";

	// Constant Text Style
	private static final String TEXT_STYLE = 
			"-fx-text-fill: #564A50; " 
			+ "-fx-font-size: 16px; "
			+ "-fx-font-family: 'Courier'; ";
	// Constant Text Style
	private static final String TEXTFIELD_STYLE = 
			"-fx-text-fill: #000000; " 
			+ "-fx-font-size: 16px; "
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
}
