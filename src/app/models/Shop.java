package app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 14:40
 */
public class Shop {

	private String ID;
	private String name;
	private List<Coupon> coupons;

	public Shop() {
		this.coupons = new ArrayList<>();
	}

	public Shop(String ID, String name) {
		this.ID = ID;
		this.name = name;
		this.coupons = new ArrayList<>();
	}

	public Shop(String ID, String name, List<Coupon> coupons) {
		this.ID = ID;
		this.name = name;
		this.coupons = coupons;
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

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Shop{" +
				"ID='" + ID + '\'' +
				", name='" + name + '\'' +
				", coupons=" + coupons +
				'}';
	}
}
