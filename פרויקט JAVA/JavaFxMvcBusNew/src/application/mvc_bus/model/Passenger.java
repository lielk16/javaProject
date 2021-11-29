package application.mvc_bus.model;

public class Passenger {
	private static int idGenerator = 1000;
	
	private int id;
	private String name;
	
	
	public Passenger(String name) {
		this.id = idGenerator++;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name + "(" + id + ")";
	}
}
