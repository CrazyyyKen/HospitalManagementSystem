// IMPORT libraries/files
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface PageManager {
	abstract String[] showInfo();

	abstract Scene add(Stage primaryStage);

	abstract Scene show(Stage primaryStage);

	abstract Scene remove(Stage primaryStage);

	abstract Scene mainPage(Stage primaryStage);
}
