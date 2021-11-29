package application.mvc_bus.view;

import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import application.mvc_bus.listeners.BusUIEventsListener;

public class BusJavaFX2 implements AbstractBusView {
	private Vector<BusUIEventsListener> allListeners = new Vector<BusUIEventsListener>();
	private Vector<Label> arrPassengersLabels = new Vector<Label>();
	private FlowPane flPnlAllPassenger = new FlowPane();

	public BusJavaFX2(Stage theStage) {
		theStage.setTitle("My Bus");
		
		GridPane gpRoot = new GridPane();
		gpRoot.setPadding(new Insets(10));
		gpRoot.setHgap(10);
		gpRoot.setVgap(10);
		
		flPnlAllPassenger.setVgap(10);
		flPnlAllPassenger.setHgap(10);
		
		Label lblName = new Label("Enter Passenger's name: ");
		TextField tfName = new TextField();
		Button btnAddPassenger = new Button("Add Passenger");
		
		btnAddPassenger.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent action) {
				for (BusUIEventsListener l : allListeners)
					l.addPassengerToUI(tfName.getText());
				
				tfName.setText("");
			}
		});
		
		Label lblAllPassengers = new Label("All Passengers:");
		
		gpRoot.add(lblName, 0, 0);
		gpRoot.add(tfName, 1, 0);
		gpRoot.add(btnAddPassenger, 2, 0);
		gpRoot.add(lblAllPassengers, 0, 1);
		gpRoot.add(flPnlAllPassenger, 0, 2, 3, 1);
		
		theStage.setScene(new Scene(gpRoot, 450, 200));
		theStage.show();
	}

	@Override
	public void registerListener(BusUIEventsListener newListener) {
		allListeners.add(newListener);
	}

	@Override
	public void addPassengerToUI(int id, String name) {	
		Label lblNewPassenger = new Label(name + " (" + id + ")");
		lblNewPassenger.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				for (BusUIEventsListener l : allListeners)
					l.removePassengerFromUI(getIdFromPassengerString(lblNewPassenger.getText()));
			}
		});
		lblNewPassenger.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
		lblNewPassenger.setPadding(new Insets(5));
		
		arrPassengersLabels.add(lblNewPassenger);
		flPnlAllPassenger.getChildren().add(lblNewPassenger);
	}

	@Override
	public void removePassengerFromUI(int id) {
		for (int i=0 ; i < arrPassengersLabels.size() ; i++) {
			int lblId = getIdFromPassengerString(arrPassengersLabels.get(i).getText());
			if (lblId == id) {
				Label theLabel = arrPassengersLabels.remove(i);
				flPnlAllPassenger.getChildren().remove(theLabel);
				return;
			}
		}
	}
	
	@Override
	public void busIsFullMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}
	
	private int getIdFromPassengerString(String str) {
		System.out.println(str);
		String[] arr = str.split("[()]");
		System.out.println(arr);
		return Integer.parseInt(arr[1]);
	}
}
