import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Principal extends Application {
	private Pane login;

	public static void main(String[] args) throws Exception {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Controle de Temperatura Fuzzy");
		primaryStage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("css/icone.png")));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Principal.class.getResource("tela.fxml"));
		login = loader.load();

		Scene scene = new Scene(login);
		scene.getStylesheets().add(getClass().getClassLoader().getResource("css/bootstrap.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest((e) -> System.exit(0));
		primaryStage.show();
	}
}
