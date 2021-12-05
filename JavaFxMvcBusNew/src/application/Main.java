package application;

import application.mvc_bus.controller.BusController;
import application.mvc_bus.model.Bus;
import application.mvc_bus.view.AbstractBusView;
import application.mvc_bus.view.BusJavaFX;
import application.mvc_bus.view.BusJavaFX2;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Bus theModel = new Bus();
		AbstractBusView theView1 = new BusJavaFX(primaryStage);
		BusController controller1 = new BusController(theModel, theView1);

		AbstractBusView theView2 = new BusJavaFX2(new Stage());
		BusController controller2 = new BusController(theModel, theView2);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
