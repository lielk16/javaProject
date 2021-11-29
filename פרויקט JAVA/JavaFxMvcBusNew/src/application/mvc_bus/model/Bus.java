package application.mvc_bus.model;

import java.util.Vector;

import application.mvc_bus.listeners.BusEventsListener;


public class Bus {	
	private Vector<Passenger> allPassengers;
	private Vector<BusEventsListener> listeners;

	public Bus() {
		allPassengers = new Vector<Passenger>();
		listeners = new Vector<BusEventsListener>();
	}
	
	public int getNumOfPassengers() {
		return allPassengers.size();
	}
	
	public void registerListener(BusEventsListener listener) {
		listeners.add(listener);
	}
	
	public Passenger addPassenger(String name) throws Exception {
		if (allPassengers.size() == 3)
			throw new Exception("Bus is full");
		
		Passenger newPassenger = new Passenger(name);
		allPassengers.add(newPassenger);
		fireAddPassengerEvent(newPassenger);
	
		return newPassenger;
	}
	
	public Passenger removePassenger(int id) {
		Passenger passengerToRemove = getPassengerById(id);
		allPassengers.remove(passengerToRemove);
		fireRemovePasengerEvent(passengerToRemove);
		
		return passengerToRemove;
	}
	
	public Passenger getPassengerById(int id) {
		for (Passenger p : allPassengers) {
			if (p.getId() == id)
				return p;
		}
		return null;
	}
	
	private void fireAddPassengerEvent(Passenger passenger) {
		for (BusEventsListener l : listeners) {
			l.addedPassengerToModelEvent(passenger.getId(), passenger.getName());
		}
	}
	
	private void fireRemovePasengerEvent(Passenger passenger) {
		for (BusEventsListener l : listeners) {
			l.removedPassengerFromModelEvent(passenger.getId());
		}
	}
}
