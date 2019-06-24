package app.dataTransferObjects;

import java.util.List;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:37
 */
public class ShopDTO {

	private String ID;
	private String name;
	private List<CouponDTO> coupons;

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

	public List<CouponDTO> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponDTO> coupons) {
		this.coupons = coupons;
	}
}
