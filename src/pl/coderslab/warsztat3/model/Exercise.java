package pl.coderslab.warsztat3.model;

public class Exercise {

	private int id;
	private String name;
	private String description;

	public Exercise() {
		this.id = 0;
		this.name = "";
		this.description = "";
	}

	public Exercise(String name, String description) {
		this.id = 0;
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
