package application.mvc_bus.controller;

import application.mvc_bus.listeners.BusEventsListener;
import application.mvc_bus.listeners.BusUIEventsListener;
import application.mvc_bus.model.Bus;
import application.mvc_bus.view.AbstractBusView;

public class BusController implements BusEventsListener, BusUIEventsListener {
    private Bus busModel;
    private AbstractBusView  busView;
    
    public BusController(Bus model, AbstractBusView view) {
        busModel = model;
        busView  = view;
        
        busModel.registerListener(this);
        busView.registerListener(this);
    }

	@Override
	public void addedPassengerToModelEvent(int id, String name) {
		busView.addPassengerToUI(id, name);
	}

	@Override
	public void removedPassengerFromModelEvent(int id) {
		busView.removePassengerFromUI(id);
	}

	@Override
	public void addPassengerToUI(String name) {
		try {
			busModel.addPassenger(name);
		} catch (Exception e) {
			busView.busIsFullMessage(e.getMessage());
		}
	}

	@Override
	public void removePassengerFromUI(int id) {
		busModel.removePassenger(id);
	}
}
