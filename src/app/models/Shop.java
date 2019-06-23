package app.models;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 14:40
 */
public class Shop {

	private String ID;
	private String name;

	public Shop(String ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"ID='" + ID + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
