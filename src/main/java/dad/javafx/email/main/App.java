package dad.javafx.email.main;

import dad.javafx.email.mvc.EnviarEmailController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	public EnviarEmailController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {

		controller= new EnviarEmailController();
		
		Scene scene= new Scene(controller.getView(),500,440);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Enviar Email");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
