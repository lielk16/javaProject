package application.mvc_bus.view;

import application.mvc_bus.listeners.BusUIEventsListener;

public interface AbstractBusView {
	void registerListener(BusUIEventsListener listener);
	void addPassengerToUI(int id, String name);
	void removePassengerFromUI(int id);
	void busIsFullMessage(String msg);
}
