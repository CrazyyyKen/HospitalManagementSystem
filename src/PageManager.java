// IMPORT libraries/files
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface PageManager {
	
	public abstract Scene mainPage(Stage primaryStage);
	
	public abstract Scene add(Stage primaryStage);
	
	public abstract String[] showInfo();

	public abstract Scene show(Stage primaryStage);

	public abstract Scene remove(Stage primaryStage);

	
}
