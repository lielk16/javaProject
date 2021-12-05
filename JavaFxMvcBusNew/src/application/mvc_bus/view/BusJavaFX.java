package application.mvc_bus.view;

import java.util.Vector;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import application.mvc_bus.listeners.BusUIEventsListener;

public class BusJavaFX /*extends Application*/ implements AbstractBusView {
	private Vector<BusUIEventsListener> allListeners = new Vector<BusUIEventsListener>();
	private ComboBox<String> cmbAllPassengers = new ComboBox<String>();
	
	public BusJavaFX(Stage theStage) {
		theStage.setTitle("My Bus");
		
		GridPane gpRoot = new GridPane();
		gpRoot.setPadding(new Insets(10));
		gpRoot.setHgap(10);
		gpRoot.setVgap(10);
		
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
		Button btnRemovePassneger = new Button("Remove Passenger");
		btnRemovePassneger.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (BusUIEventsListener l : allListeners)
					l.removePassengerFromUI(getIdFromPassengerString(cmbAllPassengers.getValue()));
			}
		});
		
		gpRoot.add(lblName, 0, 0);
		gpRoot.add(tfName, 1, 0);
		gpRoot.add(btnAddPassenger, 2, 0);
		gpRoot.add(lblAllPassengers, 0, 1);
		gpRoot.add(cmbAllPassengers, 1, 1);
		gpRoot.add(btnRemovePassneger, 2, 1);
		
		theStage.setScene(new Scene(gpRoot, 450, 120));
		theStage.show();
	}

	@Override
	public void registerListener(BusUIEventsListener newListener) {
		allListeners.add(newListener);
	}

	@Override
	public void addPassengerToUI(int id, String name) {
		cmbAllPassengers.getItems().add(name + " (" + id + ")");
	}

	@Override
	public void removePassengerFromUI(int id) {
		ObservableList<String> allLines = cmbAllPassengers.getItems();
		for (int i=0 ; i < allLines.size() ; i++) {
			int lineId = getIdFromPassengerString(allLines.get(i));
			if (lineId == id) {
				cmbAllPassengers.getItems().remove(i);
			}
		}
	}
	
	private int getIdFromPassengerString(String str) {
		System.out.println(str);
		String[] arr = str.split("[()]");
		System.out.println(arr);
		return Integer.parseInt(arr[1]);
	}

	@Override
	public void busIsFullMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
		
	}
}
