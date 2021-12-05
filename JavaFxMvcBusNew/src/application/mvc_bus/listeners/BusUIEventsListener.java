package application.mvc_bus.listeners;

public interface BusUIEventsListener {
	void addPassengerToUI(String name);
	void removePassengerFromUI(int id);
}
